package org.utl.smartmenu.data.repository

import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.dao.SupplierDao
import org.utl.smartmenu.data.entity.SupplierEntity

class SupplierRepository(private val supplierDao: SupplierDao) {
    
    val allSuppliers: Flow<List<SupplierEntity>> = supplierDao.getAllSuppliers()
    val activeSuppliers: Flow<List<SupplierEntity>> = supplierDao.getActiveSuppliers()
    
    suspend fun getSupplierById(supplierId: Long): SupplierEntity? {
        return supplierDao.getSupplierById(supplierId)
    }
    
    suspend fun insert(supplier: SupplierEntity): Long {
        return supplierDao.insertSupplier(supplier)
    }
    
    suspend fun update(supplier: SupplierEntity) {
        supplierDao.updateSupplier(supplier)
    }
    
    suspend fun delete(supplier: SupplierEntity) {
        supplierDao.deleteSupplier(supplier)
    }
    
    suspend fun updateSupplierStatus(supplierId: Long, active: Boolean) {
        supplierDao.updateSupplierStatus(supplierId, active)
    }
}
