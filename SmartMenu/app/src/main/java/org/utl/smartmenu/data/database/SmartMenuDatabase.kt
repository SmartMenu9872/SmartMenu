package org.utl.smartmenu.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.utl.smartmenu.data.dao.*
import org.utl.smartmenu.data.entity.*

@Database(
    entities = [
        UserEntity::class,
        ClientEntity::class,
        MenuItemEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        InventoryItemEntity::class,
        SupplierEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class SmartMenuDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun clientDao(): ClientDao
    abstract fun menuItemDao(): MenuItemDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun inventoryItemDao(): InventoryItemDao
    abstract fun supplierDao(): SupplierDao

    companion object {
        @Volatile
        private var INSTANCE: SmartMenuDatabase? = null

        fun getDatabase(context: Context): SmartMenuDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,   // ← CORREGIDO (antes era context.toString())
                    SmartMenuDatabase::class.java,
                    "smart_menu_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback())
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database)
                    }
                }
            }
        }

        private suspend fun populateDatabase(db: SmartMenuDatabase) {
            val userDao = db.userDao()
            val menuItemDao = db.menuItemDao()
            val inventoryItemDao = db.inventoryItemDao()

            // Usuario administrador
            userDao.insertUser(
                UserEntity(
                    username = "admin",
                    password = "admin123",
                    fullName = "Administrador del Sistema",
                    role = UserRole.ADMINISTRADOR,
                    isActive = true
                )
            )

            // Usuarios ejemplo
            userDao.insertUser(
                UserEntity(
                    username = "mesero1",
                    password = "mesero123",
                    fullName = "Juan Pérez",
                    role = UserRole.MESERO
                )
            )

            userDao.insertUser(
                UserEntity(
                    username = "cocinero1",
                    password = "cocinero123",
                    fullName = "María González",
                    role = UserRole.COCINERO
                )
            )

            // Platillos ejemplo
            val menuItems = listOf(
                MenuItemEntity(
                    name = "Tacos al Pastor",
                    description = "Tacos con carne de cerdo marinada",
                    price = 85.0,
                    category = "PLATOS_FUERTES",
                    preparationTime = 15
                ),
                MenuItemEntity(
                    name = "Enchiladas Verdes",
                    description = "Enchiladas con salsa verde y pollo",
                    price = 95.0,
                    category = "PLATOS_FUERTES",
                    preparationTime = 20
                ),
                MenuItemEntity(
                    name = "Guacamole",
                    description = "Guacamole fresco con totopos",
                    price = 65.0,
                    category = "ENTRADAS",
                    preparationTime = 10
                ),
                MenuItemEntity(
                    name = "Agua de Horchata",
                    description = "Agua fresca de horchata",
                    price = 30.0,
                    category = "BEBIDAS",
                    preparationTime = 5
                ),
                MenuItemEntity(
                    name = "Flan Napolitano",
                    description = "Flan casero",
                    price = 45.0,
                    category = "POSTRES",
                    preparationTime = 5
                )
            )

            menuItems.forEach { menuItemDao.insertMenuItem(it) }

            // Inventario ejemplo
            val inventoryItems = listOf(
                InventoryItemEntity(
                    name = "Carne de Cerdo",
                    description = "Carne para tacos al pastor",
                    currentStock = 15.0,
                    unit = "kg",
                    minimumStock = 5.0,
                    category = "CARNES"
                ),
                InventoryItemEntity(
                    name = "Aguacate",
                    description = "Aguacate hass",
                    currentStock = 50.0,
                    unit = "unidades",
                    minimumStock = 20.0,
                    category = "VEGETALES"
                ),
                InventoryItemEntity(
                    name = "Tortillas",
                    description = "Tortillas de maíz",
                    currentStock = 200.0,
                    unit = "unidades",
                    minimumStock = 100.0,
                    category = "GRANOS"
                )
            )

            inventoryItems.forEach { inventoryItemDao.insertInventoryItem(it) }
        }
    }
}
