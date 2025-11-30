package org.utl.smartmenu.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val password: String, // En producción, esto debe estar hasheado
    val fullName: String,
    val role: UserRole,
    val isActive: Boolean = true,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class UserRole {
    ADMINISTRADOR,
    MESERO,
    COCINERO
}
