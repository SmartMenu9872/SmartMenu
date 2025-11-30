package org.utl.smartmenu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.utl.smartmenu.data.entity.OrderEntity
import org.utl.smartmenu.data.entity.OrderItemEntity
import org.utl.smartmenu.data.entity.OrderStatus
import org.utl.smartmenu.data.repository.OrderRepository

data class OrderUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()
    
    val allOrders = orderRepository.allOrders.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val todayOrders = orderRepository.todayOrders.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    val activeOrdersCount = orderRepository.activeOrdersCount.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )
    
    fun getOrdersByStatus(status: OrderStatus) = orderRepository.getOrdersByStatus(status).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun getActiveOrdersByTable(tableNumber: Int) = orderRepository.getActiveOrdersByTable(tableNumber).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun getOrderItems(orderId: Long) = orderRepository.getOrderItems(orderId).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    
    fun insertOrder(order: OrderEntity, items: List<OrderItemEntity>) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val orderId = orderRepository.insert(order)
                val orderItems = items.map { it.copy(orderId = orderId) }
                orderRepository.insertOrderItems(orderItems)
                _uiState.value = OrderUiState(successMessage = "Pedido creado exitosamente")
            } catch (e: Exception) {
                _uiState.value = OrderUiState(error = "Error al crear pedido: ${e.message}")
            }
        }
    }
    
    fun updateOrder(order: OrderEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                orderRepository.update(order)
                _uiState.value = OrderUiState(successMessage = "Pedido actualizado exitosamente")
            } catch (e: Exception) {
                _uiState.value = OrderUiState(error = "Error al actualizar: ${e.message}")
            }
        }
    }
    
    fun updateOrderStatus(orderId: Long, status: OrderStatus) {
        viewModelScope.launch {
            try {
                orderRepository.updateOrderStatus(orderId, status)
                _uiState.value = OrderUiState(successMessage = "Estado actualizado")
            } catch (e: Exception) {
                _uiState.value = OrderUiState(error = "Error al actualizar estado: ${e.message}")
            }
        }
    }
    
    fun deleteOrder(order: OrderEntity) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                orderRepository.delete(order)
                _uiState.value = OrderUiState(successMessage = "Pedido eliminado exitosamente")
            } catch (e: Exception) {
                _uiState.value = OrderUiState(error = "Error al eliminar: ${e.message}")
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = OrderUiState()
    }
}
