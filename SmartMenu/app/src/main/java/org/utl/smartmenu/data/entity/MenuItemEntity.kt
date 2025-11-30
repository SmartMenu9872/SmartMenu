package org.utl.smartmenu.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val price: Double,
    val category: String,
    val isAvailable: Boolean = true,
    val preparationTime: Int = 15, // minutos
    val imageUrl: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class MenuCategory {
    ENTRADAS,
    PLATOS_FUERTES,
    SOPAS,
    ENSALADAS,
    POSTRES,
    BEBIDAS,
    ESPECIALES
}
