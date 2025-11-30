package org.utl.smartmenu.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.entity.ClientEntity

@Dao
interface ClientDao {
    
    @Query("SELECT * FROM clients ORDER BY name ASC")
    fun getAllClients(): Flow<List<ClientEntity>>
    
    @Query("SELECT * FROM clients WHERE isWalkIn = 0 ORDER BY name ASC")
    fun getRegisteredClients(): Flow<List<ClientEntity>>
    
    @Query("SELECT * FROM clients WHERE id = :clientId")
    suspend fun getClientById(clientId: Long): ClientEntity?
    
    @Query("SELECT * FROM clients WHERE phone = :phone LIMIT 1")
    suspend fun getClientByPhone(phone: String): ClientEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: ClientEntity): Long
    
    @Update
    suspend fun updateClient(client: ClientEntity)
    
    @Delete
    suspend fun deleteClient(client: ClientEntity)
    
    @Query("UPDATE clients SET totalOrders = totalOrders + 1, totalSpent = totalSpent + :amount WHERE id = :clientId")
    suspend fun updateClientStats(clientId: Long, amount: Double)
    
    @Query("SELECT * FROM clients ORDER BY totalSpent DESC LIMIT :limit")
    fun getTopClients(limit: Int = 10): Flow<List<ClientEntity>>
}
