# 🚀 Instrucciones de Compilación y Ejecución - SmartMenu

## 📋 Requisitos Previos

### Software Necesario
- ✅ **Android Studio** (última versión estable recomendada: Hedgehog o superior)
- ✅ **JDK 11** o superior
- ✅ **Android SDK** con API Level 29 o superior
- ✅ **Emulador Android** o dispositivo físico

### Especificaciones del Proyecto
- **Lenguaje**: Kotlin
- **Min SDK**: 29 (Android 10)
- **Target SDK**: 36
- **Compile SDK**: 36
- **Arquitectura**: MVVM
- **Base de Datos**: Room 2.9.0-alpha02
- **UI**: Jetpack Compose + Material Design 3

---

## 🔧 Pasos de Instalación

### 1. Abrir el Proyecto
```
1. Abre Android Studio
2. File → Open
3. Navega a: C:\Users\chumg\AndroidStudioProjects\SmartMenu
4. Selecciona la carpeta y haz clic en OK
```

### 2. Sincronizar Gradle
```
1. Espera a que Android Studio indexe el proyecto
2. Si aparece el banner "Gradle files have changed", haz clic en "Sync Now"
3. O manualmente: File → Sync Project with Gradle Files
4. Espera a que termine la sincronización (puede tardar 2-5 minutos la primera vez)
```

### 3. Verificar Configuración
```
1. Ve a File → Project Structure
2. Verifica que:
   - JDK: 11 o superior
   - Compile SDK Version: 36
   - Build Tools Version: Latest
```

---

## ▶️ Ejecutar la Aplicación

### Opción 1: Emulador Android (Recomendado para desarrollo)

#### Crear Emulador (si no tienes uno)
```
1. Tools → Device Manager (o AVD Manager)
2. Click en "Create Device"
3. Selecciona un dispositivo (recomendado: Pixel 5 o superior)
4. Selecciona una imagen del sistema:
   - API Level: 29 o superior (recomendado: 33 o 34)
   - ABI: x86_64 (más rápido) o arm64-v8a
5. Click en "Download" si es necesario
6. Configura opciones avanzadas si lo deseas
7. Click en "Finish"
```

#### Ejecutar en Emulador
```
1. Selecciona tu emulador en el dropdown de devices
2. Haz clic en el botón Run ▶️ (o presiona Shift + F10)
3. Espera a que el emulador inicie (1-2 minutos primera vez)
4. La app se instalará y ejecutará automáticamente
```

### Opción 2: Dispositivo Físico

#### Preparar el Dispositivo
```
1. En tu dispositivo Android:
   - Ve a Configuración → Acerca del teléfono
   - Toca "Número de compilación" 7 veces
   - Vuelve a Configuración → Opciones de desarrollador
   - Activa "Depuración USB"

2. Conecta el dispositivo a tu PC con cable USB
3. Autoriza la depuración USB en el dispositivo
```

#### Ejecutar en Dispositivo
```
1. Selecciona tu dispositivo en el dropdown
2. Haz clic en Run ▶️
3. La app se instalará y ejecutará
```

---

## 🐛 Solución de Problemas Comunes

### Error: "Failed to resolve: androidx.room:room-compiler"
```
Solución:
1. File → Invalidate Caches → Invalidate and Restart
2. Espera a que Android Studio reinicie
3. Sync Project with Gradle Files
```

### Error: "Manifest merger failed"
```
Solución:
1. Verifica que el AndroidManifest.xml esté correcto
2. Clean Project: Build → Clean Project
3. Rebuild Project: Build → Rebuild Project
```

### Error: "Cannot resolve symbol 'R'"
```
Solución:
1. Sync Project with Gradle Files
2. Si persiste: Build → Clean Project
3. Build → Rebuild Project
```

### Error: "Gradle sync failed"
```
Solución:
1. Verifica tu conexión a internet
2. File → Settings → Build, Execution, Deployment → Gradle
3. Verifica que "Gradle JDK" esté configurado correctamente
4. Click en "Download JDK" si es necesario
```

### Error: "KAPT errors" o Room annotation processor
```
Solución:
1. Verifica que el plugin kotlin-kapt esté en build.gradle.kts:
   plugins {
       id("kotlin-kapt")
   }
2. Sync Project with Gradle Files
```

### La app crashea al iniciar
```
Solución:
1. Revisa el Logcat (View → Tool Windows → Logcat)
2. Busca mensajes de error en rojo
3. Verifica que la API level del emulador sea >= 29
4. Clean y Rebuild el proyecto
```

### La app está muy lenta
```
Solución:
1. En el emulador, ve a Settings → Developer options
2. Reduce las animaciones:
   - Window animation scale: 0.5x
   - Transition animation scale: 0.5x
   - Animator duration scale: 0.5x
3. O usa un dispositivo físico
```

---

## 📱 Primer Uso

### Credenciales de Prueba

#### Administrador (acceso completo)
```
Usuario: admin
Contraseña: admin123
```

#### Mesero
```
Usuario: mesero1
Contraseña: mesero123
```

#### Cocinero
```
Usuario: cocinero1
Contraseña: cocina123
```

### Datos Precargados
La app viene con:
- 3 usuarios (los mencionados arriba)
- 11 platillos en el menú
- 1 cliente de mostrador por defecto

---

## 🔍 Verificación de Instalación Correcta

### Checklist Rápido
- [ ] La app se compila sin errores
- [ ] La app se instala en el emulador/dispositivo
- [ ] Puedes hacer login con las credenciales de prueba
- [ ] Puedes navegar por todos los módulos
- [ ] Puedes agregar un nuevo cliente
- [ ] Puedes agregar un nuevo item de inventario
- [ ] Puedes agregar un nuevo proveedor
- [ ] Los datos se guardan correctamente
- [ ] El logout funciona correctamente

---

## 🎨 Personalizaciones Rápidas

### Cambiar el Nombre de la App
```kotlin
// Archivo: app/src/main/res/values/strings.xml
<string name="app_name">SmartMenu</string>
```

### Cambiar el Ícono de la App
```
1. Coloca tu ícono en: app/src/main/res/mipmap-*/
2. Actualiza en AndroidManifest.xml:
   android:icon="@mipmap/ic_launcher"
```

### Cambiar los Colores del Tema
```kotlin
// Archivo: app/src/main/java/org/utl/smartmenu/ui/theme/Color.kt
val Primary = Color(0xFF6200EE)
val Secondary = Color(0xFF03DAC5)
// ... etc
```

---

## 📊 Monitoreo y Debug

### Ver Logs
```
1. View → Tool Windows → Logcat
2. Selecciona tu app en el dropdown
3. Filtra por nivel:
   - Error (rojo): Errores críticos
   - Warn (naranja): Advertencias
   - Info (verde): Información general
```

### Inspeccionar la Base de Datos
```
1. View → Tool Windows → App Inspection
2. Selecciona "Database Inspector"
3. Ejecuta la app
4. Verás todas las tablas y sus datos
5. Puedes ejecutar queries SQL personalizados
```

### Depurar la App
```
1. Coloca breakpoints (click en el margen izquierdo del código)
2. Click en Debug 🐛 (o presiona Shift + F9)
3. Usa los controles de debug:
   - Step Over (F8)
   - Step Into (F7)
   - Resume (F9)
```

---

## 📦 Generar APK

### Debug APK (para pruebas)
```
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Espera a que termine
3. Click en "locate" en la notificación
4. El APK estará en: app/build/outputs/apk/debug/
```

### Release APK (para distribución)
```
1. Build → Generate Signed Bundle / APK
2. Selecciona APK
3. Crea o selecciona un keystore
4. Completa la información del signing
5. Selecciona "release"
6. El APK firmado estará en: app/build/outputs/apk/release/
```

---

## 🧪 Testing

### Ejecutar Tests Unitarios
```
1. Click derecho en la carpeta "test"
2. Run 'Tests in...'
```

### Ejecutar Tests Instrumentados
```
1. Click derecho en la carpeta "androidTest"
2. Run 'Tests in...'
3. Selecciona un dispositivo
```

---

## 💾 Backup de Datos

### Exportar Base de Datos (desde emulador)
```
1. View → Tool Windows → Device File Explorer
2. Navega a: data/data/org.utl.smartmenu/databases/
3. Click derecho en "smart_menu_database"
4. Save As... → Guarda en tu PC
```

---

## 📞 Soporte y Recursos

### Documentación del Proyecto
- `RESUMEN_IMPLEMENTACION.md` - Resumen técnico completo
- `GUIA_USUARIO.md` - Guía de uso para usuarios finales
- `CHECKLIST_COMPLETO.md` - Checklist de implementación

### Recursos de Android
- [Android Developers](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Material Design 3](https://m3.material.io/)

### Stack Overflow Tags
- `android`
- `kotlin`
- `android-jetpack-compose`
- `android-room`

---

## ✅ Confirmación Final

Si completaste todos los pasos y la app funciona correctamente:

**¡FELICIDADES! 🎉**

Tu aplicación SmartMenu está lista para usar. Ahora puedes:
- Explorar todas las funcionalidades
- Personalizar según tus necesidades
- Agregar nuevas características
- Distribuir a usuarios finales

---

**Desarrollado con** ❤️ **usando Jetpack Compose + Room + MVVM**
**Versión**: 1.0
**Fecha**: 2024
