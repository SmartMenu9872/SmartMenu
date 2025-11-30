package org.utl.smartmenu.data.repository

import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.dao.InventoryItemDao
import org.utl.smartmenu.data.entity.InventoryItemEntity

class InventoryRepository(private val inventoryItemDao: InventoryItemDao) {
    
    val allInventoryItems: Flow<List<InventoryItemEntity>> = inventoryItemDao.getAllInventoryItems()
    val lowStockItems: Flow<List<InventoryItemEntity>> = inventoryItemDao.getLowStockItems()
    val categories: Flow<List<String>> = inventoryItemDao.getCategories()
    
    fun getInventoryItemsByCategory(category: String): Flow<List<InventoryItemEntity>> {
        return inventoryItemDao.getInventoryItemsByCategory(category)
    }
    
    suspend fun getInventoryItemById(itemId: Long): InventoryItemEntity? {
        return inventoryItemDao.getInventoryItemById(itemId)
    }
    
    suspend fun insert(item: InventoryItemEntity): Long {
        return inventoryItemDao.insertInventoryItem(item)
    }
    
    suspend fun update(item: InventoryItemEntity) {
        inventoryItemDao.updateInventoryItem(item)
    }
    
    suspend fun delete(item: InventoryItemEntity) {
        inventoryItemDao.deleteInventoryItem(item)
    }
    
    suspend fun addStock(itemId: Long, quantity: Double) {
        inventoryItemDao.addStock(itemId, quantity)
    }
    
    suspend fun reduceStock(itemId: Long, quantity: Double) {
        inventoryItemDao.reduceStock(itemId, quantity)
    }
}
