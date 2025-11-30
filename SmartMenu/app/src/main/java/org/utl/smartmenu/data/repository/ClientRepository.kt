package org.utl.smartmenu.data.repository

import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.dao.ClientDao
import org.utl.smartmenu.data.entity.ClientEntity

class ClientRepository(private val clientDao: ClientDao) {
    
    val allClients: Flow<List<ClientEntity>> = clientDao.getAllClients()
    val registeredClients: Flow<List<ClientEntity>> = clientDao.getRegisteredClients()
    
    suspend fun getClientById(clientId: Long): ClientEntity? {
        return clientDao.getClientById(clientId)
    }
    
    suspend fun getClientByPhone(phone: String): ClientEntity? {
        return clientDao.getClientByPhone(phone)
    }
    
    suspend fun insert(client: ClientEntity): Long {
        return clientDao.insertClient(client)
    }
    
    suspend fun update(client: ClientEntity) {
        clientDao.updateClient(client)
    }
    
    suspend fun delete(client: ClientEntity) {
        clientDao.deleteClient(client)
    }
    
    suspend fun updateClientStats(clientId: Long, amount: Double) {
        clientDao.updateClientStats(clientId, amount)
    }
    
    fun getTopClients(limit: Int = 10): Flow<List<ClientEntity>> {
        return clientDao.getTopClients(limit)
    }
}
