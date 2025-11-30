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
import org.utl.smartmenu.data.entity.InventoryItemEntity
import org.utl.smartmenu.viewmodel.InventoryViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(inventoryViewModel: InventoryViewModel) {
    val inventoryItems by inventoryViewModel.allInventoryItems.collectAsState()
    val lowStockItems by inventoryViewModel.lowStockItems.collectAsState()
    val uiState by inventoryViewModel.uiState.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var showLowStockOnly by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    
    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            snackbarHostState.showSnackbar(it)
            inventoryViewModel.clearMessages()
        }
    }
    
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            inventoryViewModel.clearMessages()
        }
    }
    
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Item")
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
                text = "Inventario",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Control de insumos y stock",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(Modifier.height(16.dp))
            
            // Alerta de stock bajo
            if (lowStockItems.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Advertencia",
                            tint = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "¡Alerta de Stock Bajo!",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Text(
                                text = "${lowStockItems.size} item(s) necesitan reabastecimiento",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
            
            // Filtro
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterChip(
                    selected = showLowStockOnly,
                    onClick = { showLowStockOnly = !showLowStockOnly },
                    label = { Text("Stock Bajo (${lowStockItems.size})") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = "Filtro"
                        )
                    }
                )
            }
            
            Spacer(Modifier.height(16.dp))
            
            val displayItems = if (showLowStockOnly) lowStockItems else inventoryItems
            
            if (displayItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Inventory,
                            contentDescription = "Sin items",
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            if (showLowStockOnly) "No hay items con stock bajo" else "No hay items en inventario",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (!showLowStockOnly) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "Presiona el botón + para agregar",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(displayItems) { item ->
                        InventoryItemCard(
                            item = item,
                            onAddStock = { id, qty -> inventoryViewModel.addStock(id, qty) },
                            onReduceStock = { id, qty -> inventoryViewModel.reduceStock(id, qty) },
                            onDelete = { inventoryViewModel.deleteInventoryItem(item) }
                        )
                    }
                }
            }
        }
    }
    
    if (showAddDialog) {
        AddInventoryItemDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { item ->
                inventoryViewModel.insertInventoryItem(item)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun InventoryItemCard(
    item: InventoryItemEntity,
    onAddStock: (Long, Double) -> Unit,
    onReduceStock: (Long, Double) -> Unit,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showStockDialog by remember { mutableStateOf(false) }
    val isLowStock = item.currentStock <= item.minimumStock
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isLowStock) 
                MaterialTheme.colorScheme.errorContainer 
            else 
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.category,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    if (item.description.isNotBlank()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            
            Spacer(Modifier.height(12.dp))
            
            // Stock info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            if (isLowStock) Icons.Default.Warning else Icons.Default.Inventory2,
                            contentDescription = "Stock",
                            modifier = Modifier.size(20.dp),
                            tint = if (isLowStock) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Stock: ${item.currentStock} ${item.unit}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isLowStock) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "Mínimo: ${item.minimumStock} ${item.unit}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Button(onClick = { showStockDialog = true }) {
                    Icon(Icons.Default.Edit, contentDescription = "Modificar")
                    Spacer(Modifier.width(4.dp))
                    Text("Modificar")
                }
            }
        }
    }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Item") },
            text = { Text("¿Eliminar ${item.name} del inventario?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showDeleteDialog = false
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
    
    if (showStockDialog) {
        StockDialog(
            item = item,
            onDismiss = { showStockDialog = false },
            onAddStock = { qty ->
                onAddStock(item.id, qty)
                showStockDialog = false
            },
            onReduceStock = { qty ->
                onReduceStock(item.id, qty)
                showStockDialog = false
            }
        )
    }
}

@Composable
fun StockDialog(
    item: InventoryItemEntity,
    onDismiss: () -> Unit,
    onAddStock: (Double) -> Unit,
    onReduceStock: (Double) -> Unit
) {
    var quantity by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("add") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Modificar Stock: ${item.name}") },
        text = {
            Column {
                Text("Stock actual: ${item.currentStock} ${item.unit}")
                Spacer(Modifier.height(16.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = operation == "add",
                        onClick = { operation = "add" },
                        label = { Text("Agregar") }
                    )
                    FilterChip(
                        selected = operation == "reduce",
                        onClick = { operation = "reduce" },
                        label = { Text("Reducir") }
                    )
                }
                
                Spacer(Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Cantidad (${item.unit})") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val qty = quantity.toDoubleOrNull() ?: 0.0
                    if (qty > 0) {
                        if (operation == "add") {
                            onAddStock(qty)
                        } else {
                            onReduceStock(qty)
                        }
                    }
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInventoryItemDialog(
    onDismiss: () -> Unit,
    onConfirm: (InventoryItemEntity) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var currentStock by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var minimumStock by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("CARNES") }
    var expandedCategory by remember { mutableStateOf(false) }
    
    val categories = listOf(
        "CARNES", "VEGETALES", "LACTEOS", "GRANOS", 
        "CONDIMENTOS", "BEBIDAS", "OTROS"
    )
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Item al Inventario") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = currentStock,
                        onValueChange = { currentStock = it },
                        label = { Text("Stock") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = unit,
                        onValueChange = { unit = it },
                        label = { Text("Unidad") },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = minimumStock,
                    onValueChange = { minimumStock = it },
                    label = { Text("Stock Mínimo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                
                ExposedDropdownMenuBox(
                    expanded = expandedCategory,
                    onExpandedChange = { expandedCategory = !expandedCategory }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Categoría") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    
                    ExposedDropdownMenu(
                        expanded = expandedCategory,
                        onDismissRequest = { expandedCategory = false }
                    ) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    category = cat
                                    expandedCategory = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && currentStock.isNotBlank() && unit.isNotBlank()) {
                        onConfirm(
                            InventoryItemEntity(
                                name = name,
                                description = description,
                                currentStock = currentStock.toDoubleOrNull() ?: 0.0,
                                unit = unit,
                                minimumStock = minimumStock.toDoubleOrNull() ?: 0.0,
                                category = category,
                                createdAt = Date()
                            )
                        )
                    }
                }
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
