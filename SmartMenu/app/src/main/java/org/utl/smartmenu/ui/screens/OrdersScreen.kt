package org.utl.smartmenu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.utl.smartmenu.data.entity.*
import org.utl.smartmenu.viewmodel.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    orderViewModel: OrderViewModel,
    menuViewModel: MenuViewModel,
    clientViewModel: ClientViewModel,
    authViewModel: AuthViewModel
) {
    val orders by orderViewModel.allOrders.collectAsState()
    val uiState by orderViewModel.uiState.collectAsState()
    val authState by authViewModel.authState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    
    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            snackbarHostState.showSnackbar(it)
            orderViewModel.clearMessages()
        }
    }
    
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            orderViewModel.clearMessages()
        }
    }
    
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo Pedido")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Pedidos",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Gestión de pedidos por mesa con seguimiento de estados",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(Modifier.height(16.dp))
            
            if (orders.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Sin pedidos",
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            "No hay pedidos registrados",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Presiona el botón + para crear uno",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(orders) { order ->
                        OrderCard(
                            order = order,
                            onUpdateStatus = { id, status ->
                                orderViewModel.updateOrderStatus(id, status)
                            }
                        )
                    }
                }
            }
        }
    }
    
    if (showAddDialog) {
        AddOrderDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { tableNumber, notes ->
                val currentUser = authState.currentUser
                if (currentUser != null) {
                    val newOrder = OrderEntity(
                        tableNumber = tableNumber,
                        waiterId = currentUser.id,
                        status = OrderStatus.PENDIENTE,
                        notes = notes,
                        createdAt = Date()
                    )
                    orderViewModel.insertOrder(newOrder, emptyList())
                }
                showAddDialog = false
            }
        )
    }
}

@Composable
fun OrderCard(
    order: OrderEntity,
    onUpdateStatus: (Long, OrderStatus) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when (order.status) {
                OrderStatus.PENDIENTE -> MaterialTheme.colorScheme.secondaryContainer
                OrderStatus.EN_PREPARACION -> MaterialTheme.colorScheme.tertiaryContainer
                OrderStatus.LISTO -> MaterialTheme.colorScheme.primaryContainer
                OrderStatus.SERVIDO -> MaterialTheme.colorScheme.surfaceVariant
                OrderStatus.PAGADO -> MaterialTheme.colorScheme.tertiaryContainer
                OrderStatus.CANCELADO -> MaterialTheme.colorScheme.errorContainer
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Mesa ${order.tableNumber}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pedido #${order.id}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = order.status.name,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "$${String.format("%.2f", order.total)}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Spacer(Modifier.height(8.dp))
            
            if (order.notes.isNotBlank()) {
                Text(
                    text = "Notas: ${order.notes}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(8.dp))
            }
            
            Text(
                text = dateFormat.format(order.createdAt),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            if (order.status != OrderStatus.PAGADO && order.status != OrderStatus.CANCELADO) {
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when (order.status) {
                        OrderStatus.PENDIENTE -> {
                            Button(
                                onClick = { onUpdateStatus(order.id, OrderStatus.EN_PREPARACION) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Iniciar Preparación")
                            }
                        }
                        OrderStatus.EN_PREPARACION -> {
                            Button(
                                onClick = { onUpdateStatus(order.id, OrderStatus.LISTO) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Marcar Listo")
                            }
                        }
                        OrderStatus.LISTO -> {
                            Button(
                                onClick = { onUpdateStatus(order.id, OrderStatus.SERVIDO) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Servir")
                            }
                        }
                        OrderStatus.SERVIDO -> {
                            Button(
                                onClick = { onUpdateStatus(order.id, OrderStatus.PAGADO) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Marcar Pagado")
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun AddOrderDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int, String) -> Unit
) {
    var tableNumber by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Pedido") },
        text = {
            Column {
                OutlinedTextField(
                    value = tableNumber,
                    onValueChange = { tableNumber = it },
                    label = { Text("Número de Mesa") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notas (opcional)") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (tableNumber.isNotBlank()) {
                        onConfirm(tableNumber.toIntOrNull() ?: 0, notes)
                    }
                }
            ) {
                Text("Crear")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
