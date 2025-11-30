package org.utl.smartmenu.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "orders",
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["id"],
            childColumns = ["clientId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["waiterId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("clientId"), Index("waiterId")]
)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val clientId: Long? = null,
    val waiterId: Long,
    val tableNumber: Int,
    val status: OrderStatus,
    val total: Double = 0.0,
    val notes: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val completedAt: Date? = null
)

enum class OrderStatus {
    PENDIENTE,
    EN_PREPARACION,
    LISTO,
    SERVIDO,
    PAGADO,
    CANCELADO
}
