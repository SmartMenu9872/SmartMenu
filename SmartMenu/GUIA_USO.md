# 📖 Guía de Uso - SmartMenu

## 🎯 Inicio Rápido

### 1. Primera Ejecución

Al abrir la aplicación por primera vez, verás la pantalla de Login.

### 2. Iniciar Sesión

Usa una de las siguientes credenciales:

**Administrador:**
- Usuario: `admin`
- Contraseña: `admin123`

**Mesero:**
- Usuario: `mesero1`
- Contraseña: `mesero123`

**Cocinero:**
- Usuario: `cocinero1`
- Contraseña: `cocina123`

### 3. Registrar Nuevo Usuario

Si deseas crear un nuevo usuario:
1. Click en "¿No tienes cuenta? Regístrate aquí"
2. Completa todos los campos
3. Selecciona el rol (Administrador, Mesero o Cocinero)
4. Click en "Registrarse"
5. Regresa al login e inicia sesión

## 🏠 Pantalla Principal (Home)

Después de iniciar sesión:
- Verás un mensaje de bienvenida personalizado
- Tu nombre completo y rol
- Accesos rápidos según tu rol
- Estadísticas básicas

## 📱 Navegación

### Barra Inferior de Navegación

La barra inferior contiene iconos para cada módulo:
- 🏠 **Inicio**: Pantalla principal
- 🍽️ **Menú**: Catálogo de platillos
- 🛒 **Pedidos**: Gestión de pedidos
- 👥 **Clientes**: Registro de clientes
- 📦 **Inventario**: Control de insumos
- 🚚 **Proveedores**: Gestión de proveedores
- 👤 **Usuarios**: Administración de usuarios (solo Admin)

### Cerrar Sesión

En cualquier momento, puedes cerrar sesión presionando el ícono de logout (🚪) en la esquina superior derecha.

## 📋 Uso por Módulo

### 🍽️ Módulo de Menú

**Visualización:**
- Los platillos están organizados por categorías
- Cada platillo muestra: nombre, descripción, precio
- Los platillos no disponibles tienen una etiqueta especial

**Gestión (Solo Administrador):**
- Botón ➕ para agregar nuevo platillo
- Click en platillo para editar o ver detalles
- Cambiar disponibilidad de platillos

**Datos Precargados:**
- Ensalada César - $85
- Sopa de Tortilla - $65
- Filete de Res - $285
- Pechuga a la Plancha - $165
- Tacos de Camarón - $145
- Pasta Alfredo - $135
- Flan Napolitano - $55
- Pastel de Chocolate - $75
- Agua de Horchata - $35
- Limonada Natural - $35
- Café Americano - $30

### 🛒 Módulo de Pedidos

**Visualización:**
- Lista de pedidos del día
- Cada pedido muestra:
  - Número de pedido
  - Mesa asignada
  - Estado actual
  - Hora de creación
  - Total a pagar

**Estados de Pedido:**
- 🟡 PENDIENTE: Recién creado
- 🟠 EN_PREPARACION: En cocina
- 🟢 LISTO: Para servir
- ⚪ ENTREGADO: Completado
- 🔴 CANCELADO: Cancelado

**Crear Nuevo Pedido:**
1. Click en el botón ➕
2. Seleccionar cliente o crear uno nuevo
3. Asignar número de mesa
4. Agregar platillos del menú
5. Especificar cantidades
6. Agregar notas especiales (opcional)
7. Confirmar pedido

### 👥 Módulo de Clientes

**Funciones:**
- Ver lista de clientes registrados
- Buscar clientes por nombre o teléfono
- Agregar nuevo cliente
- Ver historial de pedidos por cliente

**Cliente de Mostrador:**
- Existe un cliente por defecto para ventas rápidas
- No requiere registro previo

### 📦 Módulo de Inventario

**Gestión de Insumos:**
- Lista de ingredientes disponibles
- Stock actual de cada producto
- Alertas de stock bajo
- Unidades de medida (kg, lt, pz, etc.)
- Costo unitario

**Operaciones:**
- Agregar nuevo insumo
- Actualizar stock
- Registro de entrada/salida
- Ver alertas de reabastecimiento

### 🚚 Módulo de Proveedores

**Información:**
- Nombre del proveedor
- Datos de contacto
- Productos que ofrece
- Estado (activo/inactivo)

**Gestión:**
- Agregar nuevo proveedor
- Editar información
- Desactivar proveedores

### 👤 Módulo de Usuarios (Solo Administrador)

**Funciones Administrativas:**
- Ver todos los usuarios del sistema
- Filtrar por rol
- Activar/desactivar usuarios
- Ver fecha de creación
- Gestionar permisos

## 🔐 Roles y Permisos

### 👑 Administrador
**Acceso Total:**
- ✅ Todos los módulos
- ✅ Crear/editar/eliminar en todos los módulos
- ✅ Gestionar usuarios
- ✅ Ver reportes completos

### 🍴 Mesero
**Acceso Limitado:**
- ✅ Ver menú completo
- ✅ Crear y gestionar pedidos
- ✅ Registrar clientes
- ✅ Ver historial de pedidos
- ❌ No puede modificar menú
- ❌ No puede gestionar usuarios

### 👨‍🍳 Cocinero
**Acceso Específico:**
- ✅ Ver pedidos en preparación
- ✅ Actualizar estado de pedidos
- ✅ Consultar inventario
- ✅ Ver menú (solo lectura)
- ❌ No puede crear pedidos
- ❌ No puede gestionar usuarios

## 💡 Consejos de Uso

### Para Meseros:
1. Verifica la disponibilidad de platillos antes de tomar pedidos
2. Siempre confirma el número de mesa
3. Revisa el pedido antes de enviarlo a cocina
4. Mantén actualizado el estado de los pedidos

### Para Cocineros:
1. Prioriza los pedidos por tiempo de espera
2. Actualiza el estado cuando comiences a preparar
3. Marca como "LISTO" cuando esté terminado
4. Revisa el inventario regularmente

### Para Administradores:
1. Mantén actualizado el menú con precios correctos
2. Verifica el inventario periódicamente
3. Gestiona usuarios activos/inactivos
4. Revisa reportes de ventas
5. Mantén actualizados los datos de proveedores

## ⚠️ Solución de Problemas

### No puedo iniciar sesión
- Verifica que el usuario y contraseña sean correctos
- Las credenciales son case-sensitive (mayúsculas/minúsculas)
- Intenta con un usuario precargado primero

### No veo todos los módulos
- Verifica tu rol de usuario
- Algunos módulos solo están disponibles para ciertos roles
- Los administradores ven todos los módulos

### La aplicación se cierra
- Asegúrate de tener suficiente espacio en el dispositivo
- Verifica la versión de Android (mínimo Android 10)
- Reinicia la aplicación

### Los datos no se guardan
- Room Database guarda automáticamente
- Si algo no se guarda, verifica los mensajes de error
- Contacta al administrador del sistema

## 🎓 Mejores Prácticas

1. **Seguridad**
   - Cierra sesión al terminar tu turno
   - No compartas tus credenciales
   - Cambia tu contraseña regularmente

2. **Eficiencia**
   - Usa los filtros y búsquedas
   - Mantén la información actualizada
   - Revisa las alertas del sistema

3. **Comunicación**
   - Usa las notas de pedido para instrucciones especiales
   - Mantén actualizados los estados
   - Comunica problemas al administrador

## 📞 Soporte

Para reportar problemas o sugerencias:
- Contacta al administrador del sistema
- Usa el botón de feedback en la app (si está disponible)
- Revisa la documentación técnica

---

**¡Listo para usar SmartMenu! 🎉**

Si tienes dudas, consulta esta guía o contacta a tu administrador.
