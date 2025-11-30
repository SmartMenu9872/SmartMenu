package org.utl.smartmenu.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.entity.OrderEntity
import org.utl.smartmenu.data.entity.OrderStatus

@Dao
interface OrderDao {
    
    @Query("SELECT * FROM orders ORDER BY createdAt DESC")
    fun getAllOrders(): Flow<List<OrderEntity>>
    
    @Query("SELECT * FROM orders WHERE status = :status ORDER BY createdAt DESC")
    fun getOrdersByStatus(status: OrderStatus): Flow<List<OrderEntity>>
    
    @Query("SELECT * FROM orders WHERE id = :orderId")
    suspend fun getOrderById(orderId: Long): OrderEntity?
    
    @Query("SELECT * FROM orders WHERE tableNumber = :tableNumber AND status NOT IN ('PAGADO', 'CANCELADO') ORDER BY createdAt DESC")
    fun getActiveOrdersByTable(tableNumber: Int): Flow<List<OrderEntity>>
    
    @Query("SELECT * FROM orders WHERE clientId = :clientId ORDER BY createdAt DESC")
    fun getOrdersByClient(clientId: Long): Flow<List<OrderEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity): Long
    
    @Update
    suspend fun updateOrder(order: OrderEntity)
    
    @Delete
    suspend fun deleteOrder(order: OrderEntity)
    
    @Query("UPDATE orders SET status = :status WHERE id = :orderId")
    suspend fun updateOrderStatus(orderId: Long, status: OrderStatus)
    
    @Query("SELECT * FROM orders WHERE DATE(createdAt/1000, 'unixepoch') = DATE('now') ORDER BY createdAt DESC")
    fun getTodayOrders(): Flow<List<OrderEntity>>
    
    @Query("SELECT COUNT(*) FROM orders WHERE status NOT IN ('PAGADO', 'CANCELADO')")
    fun getActiveOrdersCount(): Flow<Int>
}
