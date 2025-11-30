package org.utl.smartmenu.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "suppliers")
data class SupplierEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val contactName: String = "",
    val phone: String,
    val email: String = "",
    val address: String = "",
    val suppliedItems: String = "", // Lista separada por comas de productos que ofrece
    val isActive: Boolean = true,
    val notes: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
