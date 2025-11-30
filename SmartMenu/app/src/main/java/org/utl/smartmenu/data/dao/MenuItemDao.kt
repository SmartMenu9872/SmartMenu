package org.utl.smartmenu.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.entity.MenuItemEntity

@Dao
interface MenuItemDao {
    
    @Query("SELECT * FROM menu_items ORDER BY name ASC")
    fun getAllMenuItems(): Flow<List<MenuItemEntity>>
    
    @Query("SELECT * FROM menu_items WHERE isAvailable = 1 ORDER BY category, name ASC")
    fun getAvailableMenuItems(): Flow<List<MenuItemEntity>>
    
    @Query("SELECT * FROM menu_items WHERE category = :category ORDER BY name ASC")
    fun getMenuItemsByCategory(category: String): Flow<List<MenuItemEntity>>
    
    @Query("SELECT * FROM menu_items WHERE id = :itemId")
    suspend fun getMenuItemById(itemId: Long): MenuItemEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItem(item: MenuItemEntity): Long
    
    @Update
    suspend fun updateMenuItem(item: MenuItemEntity)
    
    @Delete
    suspend fun deleteMenuItem(item: MenuItemEntity)
    
    @Query("UPDATE menu_items SET isAvailable = :available WHERE id = :itemId")
    suspend fun updateAvailability(itemId: Long, available: Boolean)
    
    @Query("SELECT DISTINCT category FROM menu_items ORDER BY category ASC")
    fun getCategories(): Flow<List<String>>
}
