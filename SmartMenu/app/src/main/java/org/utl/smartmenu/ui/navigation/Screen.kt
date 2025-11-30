package org.utl.smartmenu.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Users : Screen("users")
    object Menu : Screen("menu")
    object Orders : Screen("orders")
    object Clients : Screen("clients")
    object Inventory : Screen("inventory")
    object Suppliers : Screen("suppliers")
}
