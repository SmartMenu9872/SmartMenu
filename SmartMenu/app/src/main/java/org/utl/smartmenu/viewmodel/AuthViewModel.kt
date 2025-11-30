package org.utl.smartmenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.utl.smartmenu.data.entity.UserEntity
import org.utl.smartmenu.data.entity.UserRole
import org.utl.smartmenu.data.repository.UserRepository

data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val currentUser: UserEntity? = null,
    val error: String? = null
)

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()
    
    val allUsers = userRepository.allUsers.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _authState.value = _authState.value.copy(
                error = "Usuario y contraseña son requeridos"
            )
            return
        }
        
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            try {
                val user = userRepository.login(username, password)
                if (user != null) {
                    _authState.value = AuthState(
                        isAuthenticated = true,
                        currentUser = user
                    )
                } else {
                    _authState.value = AuthState(
                        error = "Usuario o contraseña incorrectos"
                    )
                }
            } catch (e: Exception) {
                _authState.value = AuthState(
                    error = "Error al iniciar sesión: ${e.message}"
                )
            }
        }
    }
    
    fun register(username: String, password: String, fullName: String, role: UserRole) {
        if (username.isBlank() || password.isBlank() || fullName.isBlank()) {
            _registerState.value = _registerState.value.copy(
                error = "Todos los campos son requeridos"
            )
            return
        }
        
        viewModelScope.launch {
            _registerState.value = _registerState.value.copy(isLoading = true, error = null)
            
            try {
                // Verificar si el usuario ya existe
                if (userRepository.usernameExists(username)) {
                    _registerState.value = RegisterState(
                        error = "El nombre de usuario ya está en uso"
                    )
                    return@launch
                }
                
                val newUser = UserEntity(
                    username = username,
                    password = password, // En producción, hashear la contraseña
                    fullName = fullName,
                    role = role
                )
                
                userRepository.insert(newUser)
                _registerState.value = RegisterState(isSuccess = true)
            } catch (e: Exception) {
                _registerState.value = RegisterState(
                    error = "Error al registrar: ${e.message}"
                )
            }
        }
    }
    
    fun logout() {
        _authState.value = AuthState()
    }
    
    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
    
    fun clearRegisterState() {
        _registerState.value = RegisterState()
    }
    
    fun getUsersByRole(role: UserRole) = userRepository.getUsersByRole(role).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.delete(user)
        }
    }
    
    fun deactivateUser(userId: Long) {
        viewModelScope.launch {
            userRepository.deactivate(userId)
        }
    }
}
