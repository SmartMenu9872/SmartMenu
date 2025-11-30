package org.utl.smartmenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.utl.smartmenu.data.entity.InventoryItemEntity
import org.utl.smartmenu.data.repository.InventoryRepository

data class InventoryUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

class InventoryViewModel(private val inventoryRepository: InventoryRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow(InventoryUiState())
    val uiState: StateFlow<InventoryUiState> = _uiState.asStateFlow()
    
    val allInventoryItems = inventoryRepository.allInventoryItems.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val lowStockItems = inventoryRepository.lowStockItems.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val categories = inventoryRepository.categories.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun getInventoryItemsByCategory(category: String) = 
        inventoryRepository.getInventoryItemsByCategory(category).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    
    fun insertInventoryItem(item: InventoryItemEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                inventoryRepository.insert(item)
                _uiState.value = InventoryUiState(successMessage = "Item agregado exitosamente")
            } catch (e: Exception) {
                _uiState.value = InventoryUiState(error = "Error al agregar: ${e.message}")
            }
        }
    }
    
    fun updateInventoryItem(item: InventoryItemEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                inventoryRepository.update(item)
                _uiState.value = InventoryUiState(successMessage = "Item actualizado exitosamente")
            } catch (e: Exception) {
                _uiState.value = InventoryUiState(error = "Error al actualizar: ${e.message}")
            }
        }
    }
    
    fun deleteInventoryItem(item: InventoryItemEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                inventoryRepository.delete(item)
                _uiState.value = InventoryUiState(successMessage = "Item eliminado exitosamente")
            } catch (e: Exception) {
                _uiState.value = InventoryUiState(error = "Error al eliminar: ${e.message}")
            }
        }
    }
    
    fun addStock(itemId: Long, quantity: Double) {
        viewModelScope.launch {
            try {
                inventoryRepository.addStock(itemId, quantity)
                _uiState.value = InventoryUiState(successMessage = "Stock agregado exitosamente")
            } catch (e: Exception) {
                _uiState.value = InventoryUiState(error = "Error al agregar stock: ${e.message}")
            }
        }
    }
    
    fun reduceStock(itemId: Long, quantity: Double) {
        viewModelScope.launch {
            try {
                inventoryRepository.reduceStock(itemId, quantity)
                _uiState.value = InventoryUiState(successMessage = "Stock reducido exitosamente")
            } catch (e: Exception) {
                _uiState.value = InventoryUiState(error = "Error al reducir stock: ${e.message}")
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = InventoryUiState()
    }
}
