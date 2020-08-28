# Movies-Kotlin

Proyecto de demostración con Movie DB, basado en una arquitectura CLEAN con MVVM.

## Funciones de la aplicación

- Los usuarios pueden ver la lista de las últimas películas.
- Los usuarios pueden hacer clic en cualquier película para ver los avances de su elección.

## Arquitectura de la aplicación
Basado en la arquitectura Clean y el patrón de repositorio.

## La aplicación incluye los siguientes componentes principales:
- Un servicio de API web.
- Un repositorio que trabaja con el servicio api, proporcionando una interfaz de datos unificada.
- Un ViewModel que proporciona datos específicos para la interfaz de usuario.
- La interfaz de usuario, que muestra una representación visual de los datos en ViewModel.

## Paquetes de aplicaciones
- data.
- ui.
- util.

## Especificaciones de la aplicación
- SDK mínimo 16
- Java (en la rama maestra) y Kotlin (en la rama kotlin_support)
- Arquitectura MVVM
- Componentes de la arquitectura de Android (LiveData, ViewModel, componente de navegación, ConstraintLayout)
- **Retrofit 2** para integración API.
- **Gson** para serialización.
- **Glide** para cargar imágenes.