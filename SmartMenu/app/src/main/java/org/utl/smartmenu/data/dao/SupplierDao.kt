package org.utl.smartmenu.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.entity.SupplierEntity

@Dao
interface SupplierDao {
    
    @Query("SELECT * FROM suppliers ORDER BY name ASC")
    fun getAllSuppliers(): Flow<List<SupplierEntity>>
    
    @Query("SELECT * FROM suppliers WHERE isActive = 1 ORDER BY name ASC")
    fun getActiveSuppliers(): Flow<List<SupplierEntity>>
    
    @Query("SELECT * FROM suppliers WHERE id = :supplierId")
    suspend fun getSupplierById(supplierId: Long): SupplierEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSupplier(supplier: SupplierEntity): Long
    
    @Update
    suspend fun updateSupplier(supplier: SupplierEntity)
    
    @Delete
    suspend fun deleteSupplier(supplier: SupplierEntity)
    
    @Query("UPDATE suppliers SET isActive = :active WHERE id = :supplierId")
    suspend fun updateSupplierStatus(supplierId: Long, active: Boolean)
}
