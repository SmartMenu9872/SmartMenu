package org.utl.smartmenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.utl.smartmenu.data.entity.MenuItemEntity
import org.utl.smartmenu.data.repository.MenuRepository

data class MenuUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

class MenuViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()
    
    val allMenuItems = menuRepository.allMenuItems.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val availableMenuItems = menuRepository.availableMenuItems.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val categories = menuRepository.categories.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun getMenuItemsByCategory(category: String) = menuRepository.getMenuItemsByCategory(category).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun insertMenuItem(item: MenuItemEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                menuRepository.insert(item)
                _uiState.value = MenuUiState(successMessage = "Platillo agregado exitosamente")
            } catch (e: Exception) {
                _uiState.value = MenuUiState(error = "Error al agregar: ${e.message}")
            }
        }
    }
    
    fun updateMenuItem(item: MenuItemEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                menuRepository.update(item)
                _uiState.value = MenuUiState(successMessage = "Platillo actualizado exitosamente")
            } catch (e: Exception) {
                _uiState.value = MenuUiState(error = "Error al actualizar: ${e.message}")
            }
        }
    }
    
    fun deleteMenuItem(item: MenuItemEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                menuRepository.delete(item)
                _uiState.value = MenuUiState(successMessage = "Platillo eliminado exitosamente")
            } catch (e: Exception) {
                _uiState.value = MenuUiState(error = "Error al eliminar: ${e.message}")
            }
        }
    }
    
    fun toggleAvailability(itemId: Long, available: Boolean) {
        viewModelScope.launch {
            try {
                menuRepository.updateAvailability(itemId, available)
            } catch (e: Exception) {
                _uiState.value = MenuUiState(error = "Error al cambiar disponibilidad: ${e.message}")
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = MenuUiState()
    }
}
