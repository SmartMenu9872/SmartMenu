package org.utl.smartmenu.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.utl.smartmenu.data.database.SmartMenuDatabase
import org.utl.smartmenu.data.repository.*

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    
    private val database by lazy { SmartMenuDatabase.getDatabase(context) }
    
    private val userRepository by lazy { UserRepository(database.userDao()) }
    private val clientRepository by lazy { ClientRepository(database.clientDao()) }
    private val menuRepository by lazy { MenuRepository(database.menuItemDao()) }
    private val orderRepository by lazy { OrderRepository(database.orderDao(), database.orderItemDao()) }
    private val inventoryRepository by lazy { InventoryRepository(database.inventoryItemDao()) }
    private val supplierRepository by lazy { SupplierRepository(database.supplierDao()) }
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(ClientViewModel::class.java) -> {
                ClientViewModel(clientRepository) as T
            }
            modelClass.isAssignableFrom(MenuViewModel::class.java) -> {
                MenuViewModel(menuRepository) as T
            }
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> {
                OrderViewModel(orderRepository) as T
            }
            modelClass.isAssignableFrom(InventoryViewModel::class.java) -> {
                InventoryViewModel(inventoryRepository) as T
            }
            modelClass.isAssignableFrom(SupplierViewModel::class.java) -> {
                SupplierViewModel(supplierRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
