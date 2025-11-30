# SmartMenu - Sistema de Gestión de Restaurante

## 📱 Descripción del Proyecto

SmartMenu es una aplicación Android completa desarrollada con **Jetpack Compose** y **Room Database** que implementa un sistema integral para la gestión de restaurantes.

## 🏗️ Arquitectura

El proyecto sigue la arquitectura **MVVM (Model-View-ViewModel)** con las siguientes capas:

```
SmartMenu/
├── data/
│   ├── entity/          # Entidades de Room (modelos de datos)
│   ├── dao/             # Data Access Objects (operaciones de BD)
│   ├── database/        # Configuración de la base de datos
│   └── repository/      # Capa de repositorio (abstracción de datos)
├── viewmodel/           # ViewModels (lógica de negocio)
├── ui/
│   ├── screens/         # Pantallas de la aplicación
│   ├── navigation/      # Sistema de navegación
│   └── theme/           # Tema y estilos
└── MainActivity.kt      # Actividad principal
```

## 📦 Módulos Implementados

### 1. **Control de Acceso** 👤
- Login con usuario y contraseña
- Registro de nuevos usuarios
- Tres roles: Administrador, Mesero, Cocinero
- Sistema de autenticación persistente

### 2. **Menú** 🍽️
- CRUD de platillos
- Categorización (Entradas, Platos Fuertes, Postres, Bebidas)
- Control de disponibilidad
- Precios y descripciones
- Tiempo de preparación

### 3. **Pedidos** 🛒
- Creación de pedidos por mesa
- Asignación de meseros
- Estados: Pendiente, En Preparación, Listo, Entregado, Cancelado
- Detalles de pedido con platillos
- Cálculo automático de totales

### 4. **Clientes** 👥
- Registro de clientes
- Cliente de mostrador (walk-in)
- Historial de pedidos
- Búsqueda de clientes

### 5. **Inventario** 📦
- Control de ingredientes e insumos
- Stock mínimo y máximo
- Alertas de stock bajo
- Costo por unidad

### 6. **Proveedores** 🚚
- Datos de contacto
- Productos ofrecidos
- Gestión de proveedores activos

## 🔑 Credenciales de Acceso

La aplicación viene con usuarios precargados:

| Usuario | Contraseña | Rol |
|---------|-----------|-----|
| admin | admin123 | Administrador |
| mesero1 | mesero123 | Mesero |
| cocinero1 | cocina123 | Cocinero |

## 🗄️ Base de Datos (Room)

### Entidades Principales:

1. **UserEntity** - Usuarios del sistema
2. **ClientEntity** - Clientes del restaurante
3. **MenuItemEntity** - Platillos del menú
4. **OrderEntity** - Pedidos
5. **OrderItemEntity** - Detalles de pedidos
6. **InventoryItemEntity** - Inventario
7. **SupplierEntity** - Proveedores

### Relaciones:
- Order → Client (Many to One)
- Order → User/Waiter (Many to One)
- OrderItem → Order (Many to One)
- OrderItem → MenuItem (Many to One)

## 🎨 Características de UI/UX

### Buenas Prácticas Implementadas:

✅ **Material Design 3** con tema dinámico
✅ **Navegación intuitiva** con BottomNavigation
✅ **Estados de carga** con CircularProgressIndicator
✅ **Mensajes de error** claros y contextuales
✅ **Validación de formularios** en tiempo real
✅ **Cards y componentes** organizados
✅ **Iconografía** clara y representativa
✅ **Feedback visual** para acciones del usuario
✅ **Accesibilidad** con contentDescription
✅ **Responsive design** con modificadores apropiados

### Sistema de Navegación:

La navegación se adapta según el rol del usuario:

- **Administrador**: Acceso a todos los módulos
- **Mesero**: Acceso a Pedidos, Menú, Clientes
- **Cocinero**: Acceso a Pedidos (vista de cocina), Inventario

## 📱 Flujo de la Aplicación

```
Login/Register
     ↓
Home (Pantalla de Bienvenida)
     ↓
Navegación por Módulos
     ├── Menú
     ├── Pedidos
     ├── Clientes
     ├── Inventario
     ├── Proveedores
     └── Usuarios (solo Admin)
```

## 🔧 Tecnologías Utilizadas

- **Kotlin** - Lenguaje de programación
- **Jetpack Compose** - UI moderna y declarativa
- **Room Database 2.6.1** - Persistencia de datos
- **Coroutines & Flow** - Programación asíncrona
- **Navigation Compose** - Sistema de navegación
- **ViewModel** - Gestión de estado
- **Material Design 3** - Diseño y componentes

## 📊 Características de Room

- ✅ DAOs con operaciones CRUD
- ✅ Relaciones con Foreign Keys
- ✅ Índices para optimización
- ✅ TypeConverters para enums
- ✅ Flow para observación reactiva
- ✅ Callback para datos iniciales
- ✅ Queries complejas con filtros

## 🚀 Compilación y Ejecución

1. Abre el proyecto en Android Studio
2. Sincroniza Gradle (Build → Sync Project with Gradle Files)
3. Ejecuta en emulador o dispositivo físico (mínimo API 29)

## 📝 Datos de Ejemplo

La base de datos se inicializa automáticamente con:
- 3 usuarios (admin, mesero, cocinero)
- 11 platillos en 4 categorías
- 1 cliente de mostrador

## 🔐 Seguridad

⚠️ **IMPORTANTE**: En un entorno de producción:
- Las contraseñas deben estar hasheadas (usar BCrypt o similar)
- Implementar tokens JWT para sesiones
- Validar permisos en el backend
- Usar HTTPS para comunicación
- Implementar rate limiting

## 📈 Mejoras Futuras

- [ ] Implementar hash de contraseñas
- [ ] Agregar reportes y estadísticas
- [ ] Sistema de reservaciones
- [ ] Notificaciones push para pedidos
- [ ] Sincronización con backend
- [ ] Modo offline completo
- [ ] Impresión de tickets
- [ ] Integración con sistemas de pago
- [ ] Dashboard analítico
- [ ] Gestión de mesas en tiempo real

## 👨‍💻 Desarrollo

Desarrollado siguiendo:
- Clean Architecture
- Principios SOLID
- Buenas prácticas de Kotlin
- Material Design Guidelines
- Android Jetpack best practices

## 📄 Licencia

Proyecto educativo para demostración de capacidades de desarrollo Android.

---

**Versión**: 1.0
**Última actualización**: 2025
**SDK Mínimo**: 29 (Android 10)
**SDK Target**: 36
