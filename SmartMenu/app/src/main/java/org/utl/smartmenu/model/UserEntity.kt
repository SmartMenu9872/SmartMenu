package org.utl.smartmenu.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String, // En producción usar hash
    val role: UserRole,
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)

enum class UserRole {
    ADMINISTRATOR,
    WAITER,
    COOK
}

// data/local/entity/ClientEntity.kt
@Entity(tableName = "clients")
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String? = null,
    val email: String? = null,
    val isWalkIn: Boolean = false, // Cliente de mostrador
    val createdAt: Long = System.currentTimeMillis()
)

// data/local/entity/MenuItemEntity.kt
@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val isAvailable: Boolean = true,
    val imageUrl: String? = null,
    val preparationTime: Int // minutos
)

// data/local/entity/OrderEntity.kt
@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tableNumber: Int,
    val clientId: Int?,
    val waiterId: Int,
    val status: OrderStatus,
    val totalAmount: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)

enum class OrderStatus {
    PENDING,
    IN_PROGRESS,
    READY,
    DELIVERED,
    CANCELLED
}

// data/local/entity/OrderItemEntity.kt
@Entity(
    tableName = "order_items",
    primaryKeys = ["orderId", "menuItemId"]
)
data class OrderItemEntity(
    val orderId: Int,
    val menuItemId: Int,
    val quantity: Int,
    val specialInstructions: String? = null,
    val status: OrderItemStatus = OrderItemStatus.PENDING
)

enum class OrderItemStatus {
    PENDING,
    PREPARING,
    READY
}

// data/local/entity/IngredientEntity.kt
@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val unit: String, // kg, L, unidades, etc.
    val currentStock: Double,
    val minStock: Double,
    val maxStock: Double,
    val lastUpdated: Long = System.currentTimeMillis()
)

// data/local/entity/SupplierEntity.kt
@Entity(tableName = "suppliers")
data class SupplierEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val contactName: String,
    val phone: String,
    val email: String? = null,
    val address: String? = null
)

// data/local/entity/SupplierIngredientEntity.kt
@Entity(
    tableName = "supplier_ingredients",
    primaryKeys = ["supplierId", "ingredientId"]
)
data class SupplierIngredientEntity(
    val supplierId: Int,
    val ingredientId: Int,
    val pricePerUnit: Double
)