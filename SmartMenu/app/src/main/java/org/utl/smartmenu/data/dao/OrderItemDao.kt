package org.utl.smartmenu.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.entity.OrderItemEntity

@Dao
interface OrderItemDao {
    
    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    fun getOrderItems(orderId: Long): Flow<List<OrderItemEntity>>
    
    @Query("SELECT * FROM order_items WHERE id = :itemId")
    suspend fun getOrderItemById(itemId: Long): OrderItemEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItem(orderItem: OrderItemEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItems(orderItems: List<OrderItemEntity>)
    
    @Update
    suspend fun updateOrderItem(orderItem: OrderItemEntity)
    
    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItemEntity)
    
    @Query("DELETE FROM order_items WHERE orderId = :orderId")
    suspend fun deleteOrderItems(orderId: Long)
    
    @Query("SELECT SUM(subtotal) FROM order_items WHERE orderId = :orderId")
    suspend fun getOrderTotal(orderId: Long): Double?
}
