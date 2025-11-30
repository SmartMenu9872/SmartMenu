package org.utl.smartmenu.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "inventory_items")
data class InventoryItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val currentStock: Double,
    val unit: String, // kg, litros, unidades, etc.
    val minimumStock: Double,
    val category: String,
    val lastRestockDate: Date = Date(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class InventoryCategory {
    CARNES,
    VEGETALES,
    LACTEOS,
    GRANOS,
    CONDIMENTOS,
    BEBIDAS,
    OTROS
}
