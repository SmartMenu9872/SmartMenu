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
import androidx.compose.ui.unit.dp
import org.utl.smartmenu.data.entity.UserEntity
import org.utl.smartmenu.viewmodel.AuthViewModel

@Composable
fun UsersScreen(authViewModel: AuthViewModel) {
    val users by authViewModel.allUsers.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título y descripción
        Text(
            text = "Gestión de Usuarios",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Control de acceso por roles: Administrador, Mesero y Cocinero",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(Modifier.height(16.dp))
        
        if (users.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.PersonOff,
                        contentDescription = "Sin usuarios",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        "No hay usuarios registrados",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { user ->
                    UserCard(
                        user = user,
                        onDelete = { authViewModel.deleteUser(user) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    user: UserEntity,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when (user.role) {
                org.utl.smartmenu.data.entity.UserRole.ADMINISTRADOR -> 
                    MaterialTheme.colorScheme.primaryContainer
                org.utl.smartmenu.data.entity.UserRole.MESERO -> 
                    MaterialTheme.colorScheme.secondaryContainer
                org.utl.smartmenu.data.entity.UserRole.COCINERO -> 
                    MaterialTheme.colorScheme.tertiaryContainer
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = when (user.role) {
                    org.utl.smartmenu.data.entity.UserRole.ADMINISTRADOR -> Icons.Default.AdminPanelSettings
                    org.utl.smartmenu.data.entity.UserRole.MESERO -> Icons.Default.TableRestaurant
                    org.utl.smartmenu.data.entity.UserRole.COCINERO -> Icons.Default.Restaurant
                },
                contentDescription = user.role.name,
                modifier = Modifier.size(40.dp),
                tint = when (user.role) {
                    org.utl.smartmenu.data.entity.UserRole.ADMINISTRADOR -> 
                        MaterialTheme.colorScheme.onPrimaryContainer
                    org.utl.smartmenu.data.entity.UserRole.MESERO -> 
                        MaterialTheme.colorScheme.onSecondaryContainer
                    org.utl.smartmenu.data.entity.UserRole.COCINERO -> 
                        MaterialTheme.colorScheme.onTertiaryContainer
                }
            )
            
            Spacer(Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.fullName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "@${user.username}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = user.role.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = when (user.role) {
                        org.utl.smartmenu.data.entity.UserRole.ADMINISTRADOR -> 
                            MaterialTheme.colorScheme.onPrimaryContainer
                        org.utl.smartmenu.data.entity.UserRole.MESERO -> 
                            MaterialTheme.colorScheme.onSecondaryContainer
                        org.utl.smartmenu.data.entity.UserRole.COCINERO -> 
                            MaterialTheme.colorScheme.onTertiaryContainer
                    }
                )
            }
            
            if (user.role != org.utl.smartmenu.data.entity.UserRole.ADMINISTRADOR) {
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar Usuario") },
            text = { Text("¿Estás seguro de eliminar a ${user.fullName}?") },
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
}
