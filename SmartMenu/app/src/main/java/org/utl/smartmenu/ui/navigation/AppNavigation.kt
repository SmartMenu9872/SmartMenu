package org.utl.smartmenu.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.utl.smartmenu.ui.screens.*
import org.utl.smartmenu.viewmodel.*

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    authViewModel: AuthViewModel,
    clientViewModel: ClientViewModel,
    menuViewModel: MenuViewModel,
    orderViewModel: OrderViewModel,
    inventoryViewModel: InventoryViewModel,
    supplierViewModel: SupplierViewModel
) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()
    
    // Si no está autenticado, mostrar login
    if (!authState.isAuthenticated) {
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    authViewModel = authViewModel,
                    onNavigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    authViewModel = authViewModel,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    } else {
        // Usuario autenticado, mostrar la app principal
        MainApp(
            navController = navController,
            authViewModel = authViewModel,
            clientViewModel = clientViewModel,
            menuViewModel = menuViewModel,
            orderViewModel = orderViewModel,
            inventoryViewModel = inventoryViewModel,
            supplierViewModel = supplierViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    clientViewModel: ClientViewModel,
    menuViewModel: MenuViewModel,
    orderViewModel: OrderViewModel,
    inventoryViewModel: InventoryViewModel,
    supplierViewModel: SupplierViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val authState by authViewModel.authState.collectAsState()
    
    val navigationItems = listOf(
        NavigationItem("Inicio", Icons.Default.Home, Screen.Home),
        NavigationItem("Usuarios", Icons.Default.Person, Screen.Users),
        NavigationItem("Menú", Icons.Default.Restaurant, Screen.Menu),
        NavigationItem("Pedidos", Icons.Default.ShoppingCart, Screen.Orders),
        NavigationItem("Clientes", Icons.Default.People, Screen.Clients),
        NavigationItem("Inventario", Icons.Default.Inventory, Screen.Inventory),
        NavigationItem("Proveedores", Icons.Default.LocalShipping, Screen.Suppliers)
    )
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(16.dp))
                
                // Header del drawer
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "SmartMenu",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Usuario: ${authState.currentUser?.fullName ?: ""}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Rol: ${authState.currentUser?.role?.name ?: ""}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                
                Divider()
                Spacer(Modifier.height(8.dp))
                
                // Items de navegación
                navigationItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.screen.route,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(item.screen.route) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                
                Spacer(Modifier.weight(1f))
                Divider()
                
                // Cerrar sesión
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Logout, contentDescription = "Cerrar Sesión") },
                    label = { Text("Cerrar Sesión") },
                    selected = false,
                    onClick = {
                        authViewModel.logout()
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(12.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = MaterialTheme.colorScheme.errorContainer
                    )
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            navigationItems.find { it.screen.route == currentRoute }?.title ?: "SmartMenu"
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(authViewModel = authViewModel)
                }
                composable(Screen.Users.route) {
                    UsersScreen(authViewModel = authViewModel)
                }
                composable(Screen.Menu.route) {
                    MenuScreen(menuViewModel = menuViewModel)
                }
                composable(Screen.Orders.route) {
                    OrdersScreen(
                        orderViewModel = orderViewModel,
                        menuViewModel = menuViewModel,
                        clientViewModel = clientViewModel,
                        authViewModel = authViewModel
                    )
                }
                composable(Screen.Clients.route) {
                    ClientsScreen(clientViewModel = clientViewModel)
                }
                composable(Screen.Inventory.route) {
                    InventoryScreen(inventoryViewModel = inventoryViewModel)
                }
                composable(Screen.Suppliers.route) {
                    SuppliersScreen(supplierViewModel = supplierViewModel)
                }
            }
        }
    }
}
