package org.utl.smartmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.utl.smartmenu.ui.navigation.AppNavigation
import org.utl.smartmenu.ui.theme.SmartMenuTheme
import org.utl.smartmenu.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartMenuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val factory = ViewModelFactory(applicationContext)
                    
                    val authViewModel: AuthViewModel = viewModel(factory = factory)
                    val clientViewModel: ClientViewModel = viewModel(factory = factory)
                    val menuViewModel: MenuViewModel = viewModel(factory = factory)
                    val orderViewModel: OrderViewModel = viewModel(factory = factory)
                    val inventoryViewModel: InventoryViewModel = viewModel(factory = factory)
                    val supplierViewModel: SupplierViewModel = viewModel(factory = factory)
                    
                    AppNavigation(
                        authViewModel = authViewModel,
                        clientViewModel = clientViewModel,
                        menuViewModel = menuViewModel,
                        orderViewModel = orderViewModel,
                        inventoryViewModel = inventoryViewModel,
                        supplierViewModel = supplierViewModel
                    )
                }
            }
        }
    }
}
