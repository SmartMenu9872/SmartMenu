package org.utl.smartmenu.data.database

import androidx.room.TypeConverter
import org.utl.smartmenu.data.entity.OrderStatus
import org.utl.smartmenu.data.entity.UserRole
import java.util.Date

class Converters {
    
    // Date Converters
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // UserRole Converters
    @TypeConverter
    fun fromUserRole(role: UserRole): String {
        return role.name
    }

    @TypeConverter
    fun toUserRole(roleString: String): UserRole {
        return UserRole.valueOf(roleString)
    }

    // OrderStatus Converters
    @TypeConverter
    fun fromOrderStatus(status: OrderStatus): String {
        return status.name
    }

    @TypeConverter
    fun toOrderStatus(statusString: String): OrderStatus {
        return OrderStatus.valueOf(statusString)
    }
}
