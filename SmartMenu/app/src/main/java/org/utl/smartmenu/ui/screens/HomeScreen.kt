package org.utl.smartmenu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.utl.smartmenu.viewmodel.AuthViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(authViewModel: AuthViewModel) {
    val authState by authViewModel.authState.collectAsState()
    val currentUser = authState.currentUser
    val dateFormat = SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", Locale("es", "MX"))
    val currentDate = dateFormat.format(Date())
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Mensaje de bienvenida
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Restaurant,
                        contentDescription = "SmartMenu",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    Text(
                        text = "¡Bienvenido/a!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = currentUser?.fullName ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    
                    Spacer(Modifier.height(4.dp))
                    
                    Text(
                        text = "Rol: ${currentUser?.role?.name ?: ""}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = currentDate,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                }
            }
        }
        
        item {
            // Descripción del sistema
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Sistema de Gestión de Restaurantes",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = "SmartMenu es una solución completa para la gestión interna de restaurantes. " +
                                "Permite controlar pedidos, inventario, menús, clientes y proveedores de manera eficiente.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        
        item {
            // Módulos disponibles
            Text(
                text = "Módulos Disponibles",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        item {
            ModuleCard(
                icon = Icons.Default.Person,
                title = "Control de Acceso",
                description = "Gestión de usuarios con roles: Administrador, Mesero y Cocinero"
            )
        }
        
        item {
            ModuleCard(
                icon = Icons.Default.Restaurant,
                title = "Menú",
                description = "Administra platillos, precios y disponibilidad del menú"
            )
        }
        
        item {
            ModuleCard(
                icon = Icons.Default.ShoppingCart,
                title = "Pedidos",
                description = "Control de pedidos por mesa, con estados y seguimiento en tiempo real"
            )
        }
        
        item {
            ModuleCard(
                icon = Icons.Default.People,
                title = "Clientes",
                description = "Registro de clientes y historial de pedidos"
            )
        }
        
        item {
            ModuleCard(
                icon = Icons.Default.Inventory,
                title = "Inventario",
                description = "Control de insumos, stock y alertas de reabastecimiento"
            )
        }
        
        item {
            ModuleCard(
                icon = Icons.Default.LocalShipping,
                title = "Proveedores",
                description = "Gestión de proveedores y productos ofrecidos"
            )
        }
        
        item {
            // Instrucciones rápidas
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "Información",
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Inicio Rápido",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = "Usa el menú lateral (☰) para navegar entre los diferentes módulos del sistema.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        }
        
        item {
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun ModuleCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(Modifier.width(16.dp))
            
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
