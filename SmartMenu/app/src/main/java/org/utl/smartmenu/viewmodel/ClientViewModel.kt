package org.utl.smartmenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.utl.smartmenu.data.entity.ClientEntity
import org.utl.smartmenu.data.repository.ClientRepository

data class ClientUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

class ClientViewModel(private val clientRepository: ClientRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ClientUiState())
    val uiState: StateFlow<ClientUiState> = _uiState.asStateFlow()
    
    val allClients = clientRepository.allClients.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val registeredClients = clientRepository.registeredClients.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun getTopClients(limit: Int = 10) = clientRepository.getTopClients(limit).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun insertClient(client: ClientEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                clientRepository.insert(client)
                _uiState.value = ClientUiState(successMessage = "Cliente registrado exitosamente")
            } catch (e: Exception) {
                _uiState.value = ClientUiState(error = "Error al registrar: ${e.message}")
            }
        }
    }
    
    fun updateClient(client: ClientEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                clientRepository.update(client)
                _uiState.value = ClientUiState(successMessage = "Cliente actualizado exitosamente")
            } catch (e: Exception) {
                _uiState.value = ClientUiState(error = "Error al actualizar: ${e.message}")
            }
        }
    }
    
    fun deleteClient(client: ClientEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                clientRepository.delete(client)
                _uiState.value = ClientUiState(successMessage = "Cliente eliminado exitosamente")
            } catch (e: Exception) {
                _uiState.value = ClientUiState(error = "Error al eliminar: ${e.message}")
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = ClientUiState()
    }
}
