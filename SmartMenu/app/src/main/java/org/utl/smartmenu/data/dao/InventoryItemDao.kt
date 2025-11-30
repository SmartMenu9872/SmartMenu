package org.utl.smartmenu.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.entity.InventoryItemEntity

@Dao
interface InventoryItemDao {
    
    @Query("SELECT * FROM inventory_items ORDER BY name ASC")
    fun getAllInventoryItems(): Flow<List<InventoryItemEntity>>
    
    @Query("SELECT * FROM inventory_items WHERE currentStock <= minimumStock ORDER BY name ASC")
    fun getLowStockItems(): Flow<List<InventoryItemEntity>>
    
    @Query("SELECT * FROM inventory_items WHERE category = :category ORDER BY name ASC")
    fun getInventoryItemsByCategory(category: String): Flow<List<InventoryItemEntity>>
    
    @Query("SELECT * FROM inventory_items WHERE id = :itemId")
    suspend fun getInventoryItemById(itemId: Long): InventoryItemEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventoryItem(item: InventoryItemEntity): Long
    
    @Update
    suspend fun updateInventoryItem(item: InventoryItemEntity)
    
    @Delete
    suspend fun deleteInventoryItem(item: InventoryItemEntity)
    
    @Query("UPDATE inventory_items SET currentStock = currentStock + :quantity WHERE id = :itemId")
    suspend fun addStock(itemId: Long, quantity: Double)
    
    @Query("UPDATE inventory_items SET currentStock = currentStock - :quantity WHERE id = :itemId")
    suspend fun reduceStock(itemId: Long, quantity: Double)
    
    @Query("SELECT DISTINCT category FROM inventory_items ORDER BY category ASC")
    fun getCategories(): Flow<List<String>>
}
