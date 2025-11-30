package org.utl.smartmenu.data.repository

import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.dao.OrderDao
import org.utl.smartmenu.data.dao.OrderItemDao
import org.utl.smartmenu.data.entity.OrderEntity
import org.utl.smartmenu.data.entity.OrderItemEntity
import org.utl.smartmenu.data.entity.OrderStatus

class OrderRepository(
    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao
) {
    
    val allOrders: Flow<List<OrderEntity>> = orderDao.getAllOrders()
    val todayOrders: Flow<List<OrderEntity>> = orderDao.getTodayOrders()
    val activeOrdersCount: Flow<Int> = orderDao.getActiveOrdersCount()
    
    fun getOrdersByStatus(status: OrderStatus): Flow<List<OrderEntity>> {
        return orderDao.getOrdersByStatus(status)
    }
    
    suspend fun getOrderById(orderId: Long): OrderEntity? {
        return orderDao.getOrderById(orderId)
    }
    
    fun getActiveOrdersByTable(tableNumber: Int): Flow<List<OrderEntity>> {
        return orderDao.getActiveOrdersByTable(tableNumber)
    }
    
    fun getOrdersByClient(clientId: Long): Flow<List<OrderEntity>> {
        return orderDao.getOrdersByClient(clientId)
    }
    
    suspend fun insert(order: OrderEntity): Long {
        return orderDao.insertOrder(order)
    }
    
    suspend fun update(order: OrderEntity) {
        orderDao.updateOrder(order)
    }
    
    suspend fun delete(order: OrderEntity) {
        orderDao.deleteOrder(order)
    }
    
    suspend fun updateOrderStatus(orderId: Long, status: OrderStatus) {
        orderDao.updateOrderStatus(orderId, status)
    }
    
    // Order Items
    fun getOrderItems(orderId: Long): Flow<List<OrderItemEntity>> {
        return orderItemDao.getOrderItems(orderId)
    }
    
    suspend fun getOrderItemById(itemId: Long): OrderItemEntity? {
        return orderItemDao.getOrderItemById(itemId)
    }
    
    suspend fun insertOrderItem(orderItem: OrderItemEntity): Long {
        return orderItemDao.insertOrderItem(orderItem)
    }
    
    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>) {
        orderItemDao.insertOrderItems(orderItems)
    }
    
    suspend fun updateOrderItem(orderItem: OrderItemEntity) {
        orderItemDao.updateOrderItem(orderItem)
    }
    
    suspend fun deleteOrderItem(orderItem: OrderItemEntity) {
        orderItemDao.deleteOrderItem(orderItem)
    }
    
    suspend fun deleteOrderItems(orderId: Long) {
        orderItemDao.deleteOrderItems(orderId)
    }
    
    suspend fun getOrderTotal(orderId: Long): Double {
        return orderItemDao.getOrderTotal(orderId) ?: 0.0
    }
}
