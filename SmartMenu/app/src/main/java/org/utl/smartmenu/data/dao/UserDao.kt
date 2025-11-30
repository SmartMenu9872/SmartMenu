package org.utl.smartmenu.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.utl.smartmenu.data.entity.UserEntity
import org.utl.smartmenu.data.entity.UserRole

@Dao
interface UserDao {
    
    @Query("SELECT * FROM users WHERE isActive = 1 ORDER BY fullName ASC")
    fun getAllUsers(): Flow<List<UserEntity>>
    
    @Query("SELECT * FROM users WHERE role = :role AND isActive = 1")
    fun getUsersByRole(role: UserRole): Flow<List<UserEntity>>
    
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Long): UserEntity?
    
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE username = :username AND password = :password AND isActive = 1 LIMIT 1")
    suspend fun login(username: String, password: String): UserEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
    
    @Query("UPDATE users SET isActive = 0 WHERE id = :userId")
    suspend fun deactivateUser(userId: Long)
    
    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    suspend fun usernameExists(username: String): Int
}
