# 🍽️ SmartMenu - Guía de Uso Rápida

## 🚀 Inicio Rápido

### 1. Compilar el Proyecto
1. Abre Android Studio
2. Haz clic en "Sync Project with Gradle Files"
3. Espera a que termine la sincronización
4. Haz clic en el botón ▶️ Run

### 2. Primer Login
Al abrir la app, verás la pantalla de login. Usa estas credenciales:

```
👤 Administrador
Usuario: admin
Contraseña: admin123

👤 Mesero
Usuario: mesero1
Contraseña: mesero123

👤 Cocinero
Usuario: cocinero1
Contraseña: cocina123
```

## 📱 Navegación Principal

### 🏠 Home (Inicio)
- **Función**: Pantalla de bienvenida
- **Qué verás**: 
  - Tu nombre y rol
  - Fecha actual
  - Accesos rápidos según tu rol
  - Estadísticas básicas

### 🍽️ Menú
- **Función**: Gestionar platillos del restaurante
- **Puedes hacer**:
  - ➕ Agregar nuevos platillos
  - 🔍 Buscar platillos
  - 🏷️ Filtrar por categoría
  - ✏️ Editar platillos existentes
  - 🗑️ Eliminar platillos
  - 🔄 Activar/desactivar disponibilidad

**Cómo agregar un platillo:**
1. Presiona el botón ➕ flotante
2. Llena los campos:
   - Nombre
   - Descripción
   - Precio
   - Categoría
   - Tiempo de preparación
3. Presiona "Agregar"

### 🛒 Pedidos
- **Función**: Gestionar órdenes del restaurante
- **Puedes hacer**:
  - ➕ Crear nuevos pedidos
  - 📋 Ver pedidos pendientes
  - 🍳 Ver pedidos en preparación
  - ✅ Marcar pedidos como listos
  - 🚚 Marcar pedidos como entregados

**Cómo crear un pedido:**
1. Presiona el botón ➕ flotante
2. Selecciona un cliente
3. Indica el número de mesa
4. Agrega platillos al pedido
5. Confirma el pedido

### 👥 Clientes
- **Función**: Gestionar clientes del restaurante
- **Puedes hacer**:
  - ➕ Registrar nuevos clientes
  - 🔍 Buscar clientes
  - ✏️ Editar información de clientes
  - 🗑️ Eliminar clientes
  - 🚶 Marcar como cliente de mostrador

**Cómo registrar un cliente:**
1. Presiona el botón ➕ flotante
2. Llena los datos:
   - Nombre (requerido)
   - Teléfono (opcional)
   - Email (opcional)
   - Dirección (opcional)
   - Marca "Cliente de mostrador" si aplica
3. Presiona "Agregar"

### 📦 Inventario
- **Función**: Controlar insumos e ingredientes
- **Puedes hacer**:
  - ➕ Agregar items al inventario
  - 🔍 Buscar items
  - ⚠️ Ver items con stock bajo
  - ⬆️⬇️ Ajustar cantidades
  - ✏️ Editar información
  - 🗑️ Eliminar items

**Cómo agregar un item:**
1. Presiona el botón ➕ flotante
2. Completa los datos:
   - Nombre (ej: "Tomates")
   - Categoría (ej: "Verduras")
   - Cantidad actual
   - Unidad de medida (ej: "kg")
   - Stock mínimo
   - Stock máximo
3. Presiona "Agregar"

**Cómo ajustar el stock:**
1. Presiona el ícono ⬆️⬇️ en una tarjeta
2. Ingresa la cantidad
3. Selecciona "Agregar" o "Reducir"
4. Confirma

**⚠️ Alertas de Stock Bajo:**
- Los items con cantidad ≤ stock mínimo aparecen en **rojo**
- Usa el filtro "Stock Bajo" para verlos rápidamente

### 🚚 Proveedores
- **Función**: Gestionar proveedores
- **Puedes hacer**:
  - ➕ Registrar proveedores
  - 🔍 Buscar proveedores
  - ✅ Filtrar solo activos
  - ✏️ Editar información
  - 🗑️ Eliminar proveedores

**Cómo registrar un proveedor:**
1. Presiona el botón ➕ flotante
2. Completa:
   - Nombre (requerido)
   - Persona de contacto
   - Teléfono (requerido)
   - Email
   - Dirección
   - Productos ofrecidos (requerido)
3. Presiona "Agregar"

### 👤 Usuarios (Solo Administrador)
- **Función**: Gestionar usuarios del sistema
- **Puedes hacer**:
  - ➕ Registrar nuevos usuarios
  - 👁️ Ver todos los usuarios
  - 🎭 Asignar roles

**Cómo registrar un usuario:**
1. Ve a la pestaña "Usuarios"
2. Presiona "Registrar Nuevo Usuario"
3. Completa:
   - Nombre completo
   - Usuario (único)
   - Contraseña
   - Rol (Administrador/Mesero/Cocinero)
4. Presiona "Registrar"

## 🎨 Interfaz

### Iconos Principales
- ➕ **FAB (Floating Action Button)**: Agregar nuevo elemento
- 🔍 **Lupa**: Buscar
- ✏️ **Lápiz**: Editar
- 🗑️ **Papelera**: Eliminar
- 🔄 **Flechas circulares**: Actualizar/Refrescar
- ⬆️⬇️ **Flechas**: Ajustar cantidad
- 🚪 **Puerta**: Cerrar sesión

### Colores de Estado
- 🟢 **Verde**: Activo/Disponible/Listo
- 🟡 **Amarillo**: En proceso/Pendiente
- 🔴 **Rojo**: Inactivo/Stock bajo/Error
- 🔵 **Azul**: Información/Normal

## ⚙️ Funciones Especiales

### 🔍 Búsqueda en Tiempo Real
- Escribe en el campo de búsqueda
- Los resultados se filtran automáticamente
- Presiona la ❌ para limpiar

### 🏷️ Filtros
- Usa los chips de filtro para categorías
- Los filtros se pueden combinar con búsqueda
- Los filtros activos se muestran seleccionados

### 🔄 Actualización Automática
- Los datos se actualizan automáticamente
- No necesitas refrescar manualmente
- Los cambios se reflejan inmediatamente en todas las pantallas

### 🚪 Cerrar Sesión
- Presiona el ícono de logout en la barra superior
- Volverás a la pantalla de login
- Tu sesión se cierra de forma segura

## 💡 Tips y Mejores Prácticas

### Para Administradores
1. Configura primero el **inventario** con todos tus insumos
2. Registra los **proveedores** principales
3. Crea el **menú** completo
4. Registra a los **usuarios** (meseros y cocineros)
5. Opcionalmente, pre-registra **clientes frecuentes**

### Para Meseros
1. Familiarízate con el **menú** y precios
2. Ten a mano la lista de **mesas disponibles**
3. Verifica la **disponibilidad** de platillos antes de tomar pedidos
4. Actualiza el **estado de pedidos** regularmente

### Para Cocineros
1. Revisa los **pedidos pendientes** constantemente
2. Consulta el **inventario** antes de iniciar preparaciones
3. Actualiza el **estado** cuando termines un platillo
4. Reporta **insumos con stock bajo** al administrador

## ❗ Solución de Problemas

### "No puedo hacer login"
- Verifica que escribiste correctamente usuario y contraseña
- Las credenciales son sensibles a mayúsculas/minúsculas
- Usa las credenciales de prueba proporcionadas

### "No veo ciertos módulos"
- Algunos módulos son exclusivos por rol
- **Usuarios** solo es visible para Administradores
- Cierra sesión y entra con una cuenta de Administrador

### "Los datos no se guardan"
- Verifica que llenaste todos los campos **requeridos** (marcados con *)
- Revisa que no haya mensajes de error en rojo
- Asegúrate de presionar el botón "Guardar" o "Agregar"

### "La app se cierra"
- Haz un Clean & Rebuild en Android Studio
- Sincroniza el proyecto con Gradle
- Verifica que el emulador tenga suficiente memoria

## 📊 Datos de Ejemplo

La app viene con datos precargados para que puedas probarla:

- **3 usuarios** con diferentes roles
- **11 platillos** en el menú
- **1 cliente** de mostrador por defecto
- Categorías: Entradas, Platos Fuertes, Postres, Bebidas

## 🔄 Flujo de Trabajo Recomendado

### Flujo Diario (Mesero)
1. Hacer login
2. Ver pedidos pendientes en Home
3. Ir a Pedidos → Crear nuevo pedido
4. Seleccionar cliente y mesa
5. Agregar platillos del menú
6. Confirmar pedido
7. Actualizar estados según avance

### Flujo Diario (Cocinero)
1. Hacer login
2. Ver pedidos en Home
3. Ir a Pedidos → Ver pendientes
4. Revisar ingredientes en Inventario
5. Marcar pedidos como "En Preparación"
6. Marcar como "Listo" al terminar

### Flujo Semanal (Administrador)
1. Revisar inventario completo
2. Identificar items con stock bajo
3. Contactar proveedores necesarios
4. Registrar nueva mercancía
5. Revisar menú y ajustar precios
6. Gestionar usuarios si es necesario

## 📞 Soporte

Si encuentras algún problema:
1. Verifica esta guía primero
2. Revisa el archivo RESUMEN_IMPLEMENTACION.md
3. Consulta los logs de Android Studio
4. Verifica que todas las dependencias estén sincronizadas

---

**¡Disfruta usando SmartMenu!** 🎉
