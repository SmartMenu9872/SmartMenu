package org.utl.smartmenu.data.repository

import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.dao.MenuItemDao
import org.utl.smartmenu.data.entity.MenuItemEntity

class MenuRepository(private val menuItemDao: MenuItemDao) {
    
    val allMenuItems: Flow<List<MenuItemEntity>> = menuItemDao.getAllMenuItems()
    val availableMenuItems: Flow<List<MenuItemEntity>> = menuItemDao.getAvailableMenuItems()
    val categories: Flow<List<String>> = menuItemDao.getCategories()
    
    fun getMenuItemsByCategory(category: String): Flow<List<MenuItemEntity>> {
        return menuItemDao.getMenuItemsByCategory(category)
    }
    
    suspend fun getMenuItemById(itemId: Long): MenuItemEntity? {
        return menuItemDao.getMenuItemById(itemId)
    }
    
    suspend fun insert(item: MenuItemEntity): Long {
        return menuItemDao.insertMenuItem(item)
    }
    
    suspend fun update(item: MenuItemEntity) {
        menuItemDao.updateMenuItem(item)
    }
    
    suspend fun delete(item: MenuItemEntity) {
        menuItemDao.deleteMenuItem(item)
    }
    
    suspend fun updateAvailability(itemId: Long, available: Boolean) {
        menuItemDao.updateAvailability(itemId, available)
    }
}
