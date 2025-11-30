package org.utl.smartmenu.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "clients")
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val phone: String = "",
    val email: String = "",
    val isWalkIn: Boolean = false, // Cliente de mostrador
    val totalOrders: Int = 0,
    val totalSpent: Double = 0.0,
    val createdAt: Date = Date()
)
