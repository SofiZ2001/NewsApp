# NewsApp

Una aplicación de noticias que permite visualizar una lista de noticias, filtrarlos por título, listar usuarios y explorar su ubicación en el mapa.

## 📋 Tabla de Contenidos
1. **Descripción General**
2. **Estructura del Proyecto**
3. **Tecnologías Utilizadas**
4. **Pruebas Unitarias**
5. **Futuras Mejoras**

## 📖 Descripción General

**NewsApp** es una aplicación que:
- Muestra una lista de noticias.
- Permite buscar noticias por título.
- Muestra una lista de usuarios.
- Proporciona un mapa interactivo para ver la ubicación asociada al usuario.
  
## 🏗️ Estructura del Proyecto

Esta aplicación utiliza la arquitectura **Clean Architecture** con el fin de tener un proyecto mejor estructurado, escalable, organizado  y con las responsabilidades de cada capa bien definidas.
Está dividido en tres capas principales:

1. **Capa de Presentación**:
   - Implementada con Jetpack Compose para vistas declarativas.
   - Uso de ViewModels para manejar estados y lógica de UI.
   - Navegación gestionada con Jetpack Navigation Component.

2. **Capa de Dominio**:
   - Contiene los casos de uso que definen las reglas de negocio.
   - En este caso el UseCase actúa como intermediario entre la capa de presentación y datos, no presenta más logica.

3. **Capa de Datos**:
   - Gestiona las fuentes de datos, como APIs REST.
   - Retrofit se utiliza para las llamadas de red.
   - Los repository se encargarán de procesar la respuesta obtenida
  
 4. **Inyección de dependencias**:
    - Se agregó un modulo más para asi poder manejar la injección de dependencias de una mejor forma
    - Para ello se utilizó **Hilt**

  ## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Kotlin
- **Arquitectura**: Clean Architecture + MVVM
- **UI**: Jetpack Compose
- **Inyección de Dependencias**: Dagger Hilt
- **Red**: Retrofit
- **Mapas**: Google Maps API
- **Gestión de Estados**: StateFlow
- **Pruebas Unitarias**: JUnit, MockK, kotlinx.coroutines-test

## ✅ Pruebas Unitarias
Se implementaron pruebas unitarias para validar la logica de:
- ViewMode
  *Coverage*
  ![image](https://github.com/user-attachments/assets/804ef570-c213-4b84-acd2-a0780a71762f)
  ![image](https://github.com/user-attachments/assets/742f6d02-5f9b-416d-9e63-4cef3009c2ca)

- Repository
  *Coverage*
![image](https://github.com/user-attachments/assets/f873cf7e-342d-47dc-aba4-b99a163c0a00)

Debido a que no representaba lógica no se realizaron pruebas para los UseCase.

## 🔮 Futuras Mejoras
- Cambiar navigation component por navigation compose.
- Investigar de mejores prácticas para manejar respuestas de servicios.
- Seguir mejorando la UI de las pantallas.


