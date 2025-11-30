package org.utl.smartmenu.data.repository

import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.dao.UserDao
import org.utl.smartmenu.data.entity.UserEntity
import org.utl.smartmenu.data.entity.UserRole

class UserRepository(private val userDao: UserDao) {
    
    val allUsers: Flow<List<UserEntity>> = userDao.getAllUsers()
    
    fun getUsersByRole(role: UserRole): Flow<List<UserEntity>> {
        return userDao.getUsersByRole(role)
    }
    
    suspend fun getUserById(userId: Long): UserEntity? {
        return userDao.getUserById(userId)
    }
    
    suspend fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }
    
    suspend fun login(username: String, password: String): UserEntity? {
        return userDao.login(username, password)
    }
    
    suspend fun insert(user: UserEntity): Long {
        return userDao.insertUser(user)
    }
    
    suspend fun update(user: UserEntity) {
        userDao.updateUser(user)
    }
    
    suspend fun delete(user: UserEntity) {
        userDao.deleteUser(user)
    }
    
    suspend fun deactivate(userId: Long) {
        userDao.deactivateUser(userId)
    }
    
    suspend fun usernameExists(username: String): Boolean {
        return userDao.usernameExists(username) > 0
    }
}
