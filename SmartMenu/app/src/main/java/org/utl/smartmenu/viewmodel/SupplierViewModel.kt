package org.utl.smartmenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.utl.smartmenu.data.entity.SupplierEntity
import org.utl.smartmenu.data.repository.SupplierRepository

data class SupplierUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

class SupplierViewModel(private val supplierRepository: SupplierRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SupplierUiState())
    val uiState: StateFlow<SupplierUiState> = _uiState.asStateFlow()
    
    val allSuppliers = supplierRepository.allSuppliers.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val activeSuppliers = supplierRepository.activeSuppliers.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun insertSupplier(supplier: SupplierEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                supplierRepository.insert(supplier)
                _uiState.value = SupplierUiState(successMessage = "Proveedor agregado exitosamente")
            } catch (e: Exception) {
                _uiState.value = SupplierUiState(error = "Error al agregar: ${e.message}")
            }
        }
    }
    
    fun updateSupplier(supplier: SupplierEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                supplierRepository.update(supplier)
                _uiState.value = SupplierUiState(successMessage = "Proveedor actualizado exitosamente")
            } catch (e: Exception) {
                _uiState.value = SupplierUiState(error = "Error al actualizar: ${e.message}")
            }
        }
    }
    
    fun deleteSupplier(supplier: SupplierEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                supplierRepository.delete(supplier)
                _uiState.value = SupplierUiState(successMessage = "Proveedor eliminado exitosamente")
            } catch (e: Exception) {
                _uiState.value = SupplierUiState(error = "Error al eliminar: ${e.message}")
            }
        }
    }
    
    fun toggleSupplierStatus(supplierId: Long, active: Boolean) {
        viewModelScope.launch {
            try {
                supplierRepository.updateSupplierStatus(supplierId, active)
                _uiState.value = SupplierUiState(successMessage = "Estado actualizado")
            } catch (e: Exception) {
                _uiState.value = SupplierUiState(error = "Error al actualizar estado: ${e.message}")
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = SupplierUiState()
    }
}
