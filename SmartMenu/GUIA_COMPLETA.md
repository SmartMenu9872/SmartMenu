# SmartMenu - Sistema de Gestión de Restaurantes

## Descripción
SmartMenu es una aplicación completa de Android desarrollada con Jetpack Compose para la gestión interna de restaurantes. Implementa arquitectura MVVM y Room Database 2.9.x para el almacenamiento de datos.

## Características Principales

### 1. Sistema de Autenticación
- **Login seguro** con usuario y contraseña
- **Registro de usuarios** con roles diferenciados:
  - Administrador
  - Mesero
  - Cocinero

### 2. Módulos del Sistema

#### Control de Acceso
- Gestión de usuarios por roles
- Visualización de todos los usuarios registrados
- Eliminación de usuarios (excepto administradores)

#### Menú
- Registro de platillos con:
  - Nombre, descripción y precio
  - Categoría (Entradas, Platos Fuertes, Sopas, Ensaladas, Postres, Bebidas, Especiales)
  - Tiempo de preparación
  - Estado de disponibilidad
- Activar/desactivar platillos
- Eliminar platillos del menú

#### Pedidos
- Crear pedidos por mesa
- Estados de pedido:
  - Pendiente
  - En Preparación
  - Listo
  - Servido
  - Pagado
  - Cancelado
- Seguimiento en tiempo real del estado de cada pedido
- Notas especiales por pedido

#### Clientes
- Registro de clientes con:
  - Nombre, teléfono y email
  - Opción de cliente de mostrador
- Historial de pedidos
- Total gastado y número de pedidos

#### Inventario
- Control de insumos con:
  - Stock actual y unidad de medida
  - Stock mínimo
  - Categorías (Carnes, Vegetales, Lácteos, Granos, Condimentos, Bebidas, Otros)
- Alertas de stock bajo
- Agregar y reducir stock
- Fecha de último reabastecimiento

#### Proveedores
- Registro de proveedores con:
  - Datos de contacto completos
  - Productos ofrecidos
  - Estado activo/inactivo
  - Notas adicionales

## Credenciales de Acceso por Defecto

La aplicación incluye usuarios de prueba precargados:

- **Administrador**: `admin` / `admin123`
- **Mesero**: `mesero1` / `mesero123`
- **Cocinero**: `cocinero1` / `cocinero123`

## Arquitectura Técnica

### Tecnologías Utilizadas
- **Jetpack Compose**: UI moderna y declarativa
- **Room Database 2.9.x**: Persistencia de datos local
- **MVVM**: Arquitectura limpia y mantenible
- **Kotlin Coroutines y Flow**: Programación asíncrona
- **Navigation Compose**: Navegación entre pantallas
- **Material Design 3**: Diseño moderno y consistente

### Estructura del Proyecto

```
org.utl.smartmenu/
├── data/
│   ├── dao/              # Interfaces de acceso a datos
│   ├── database/         # Configuración de Room Database
│   ├── entity/           # Entidades de la base de datos
│   └── repository/       # Repositorios (capa de abstracción)
├── ui/
│   ├── navigation/       # Sistema de navegación
│   ├── screens/          # Pantallas de la aplicación
│   └── theme/            # Tema y estilos
├── viewmodel/            # ViewModels (lógica de negocio)
└── MainActivity.kt       # Punto de entrada
```

### Base de Datos

La aplicación utiliza 7 tablas principales:

1. **users**: Usuarios del sistema con roles
2. **clients**: Clientes del restaurante
3. **menu_items**: Platillos del menú
4. **orders**: Pedidos principales
5. **order_items**: Items de cada pedido
6. **inventory_items**: Insumos e inventario
7. **suppliers**: Proveedores

## Flujo de Uso

### Inicio de Sesión
1. Abrir la aplicación
2. Ingresar usuario y contraseña
3. O registrar un nuevo usuario con el botón "Regístrate aquí"

### Navegación Principal
- Usar el menú lateral (botón ☰) para acceder a los módulos
- Cada módulo tiene un ícono distintivo
- Cerrar sesión desde el menú lateral

### Creación de Registros
- Cada módulo tiene un botón flotante (+) para crear nuevos registros
- Llenar el formulario correspondiente
- Confirmar para guardar

### Edición y Eliminación
- Los registros se muestran en tarjetas (Cards)
- Usar los controles dentro de cada tarjeta para editar o eliminar
- Las eliminaciones requieren confirmación

## Buenas Prácticas Implementadas

### Experiencia de Usuario (UX)
- **Feedback visual**: Snackbars para mostrar mensajes de éxito o error
- **Estados de carga**: Indicadores de progreso durante operaciones asíncronas
- **Confirmaciones**: Diálogos de confirmación para acciones destructivas
- **Navegación intuitiva**: Menú lateral con iconografía clara
- **Accesibilidad**: Uso de content descriptions para screen readers

### Desarrollo
- **Separación de responsabilidades**: Arquitectura MVVM limpia
- **Inyección de dependencias**: ViewModelFactory para crear ViewModels
- **Estados inmutables**: Uso de StateFlow para estados reactivos
- **Manejo de errores**: Try-catch en operaciones de base de datos
- **Código limpio**: Nombres descriptivos y comentarios donde necesario

### Base de Datos
- **Relaciones**: Foreign Keys para mantener integridad referencial
- **Índices**: Para optimizar consultas frecuentes
- **Type Converters**: Para tipos de datos personalizados (Date, Enums)
- **Cascadas**: Eliminación en cascada para mantener consistencia

## Compilación y Ejecución

### Requisitos
- Android Studio Hedgehog (2023.1.1) o superior
- SDK de Android 29 o superior
- JDK 11 o superior

### Pasos
1. Abrir el proyecto en Android Studio
2. Esperar a que Gradle sincronice las dependencias
3. Compilar el proyecto (Build > Make Project)
4. Ejecutar en emulador o dispositivo físico

## Notas Importantes

- **Contraseñas**: En esta versión de demostración, las contraseñas se almacenan en texto plano. En producción, deben hashearse con bcrypt o similar.
- **Datos de prueba**: La aplicación incluye datos de ejemplo que se crean automáticamente en la primera ejecución.
- **Stock mínimo**: El sistema alerta cuando el stock actual es menor o igual al stock mínimo configurado.
- **Roles**: Todos los roles tienen acceso a todos los módulos. En producción, se pueden implementar restricciones por rol.

## Desarrollado con ❤️ para SmartMenu
