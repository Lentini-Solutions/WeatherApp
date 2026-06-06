# CursosANT

CursosANT es una app Android educativa desarrollada con Kotlin y Jetpack Compose para consultar el clima actual de una ciudad, guardar resultados localmente y administrar las ciudades almacenadas.

La aplicacion consume WeatherAPI mediante Retrofit, transforma la respuesta remota a modelos propios, persiste ciudad y clima en Room, y permite consultar la informacion guardada cuando no hay conexion disponible. Tambien incluye una vista de ciudades donde se pueden ver los registros persistidos, eliminarlos y abrir su ubicacion en una app de mapas.

## Funcionalidades

- Busqueda de clima actual por nombre de ciudad.
- Visualizacion de temperatura, descripcion, viento, ciudad, pais e icono del clima.
- Persistencia local de ciudades y datos climaticos.
- Listado reactivo de ciudades guardadas con `Flow` desde Room.
- Recuperacion de clima por ciudad guardada.
- Fallback local cuando no hay conexion a internet.
- Eliminacion transaccional de ciudad y clima asociado.
- Apertura de coordenadas en una aplicacion externa de mapas.
- Navegacion inferior entre las secciones Clima y Ciudades.

## Stack tecnico

- Kotlin
- Android SDK 36
- Jetpack Compose
- Material 3
- Navigation Compose
- MVVM
- Koin para inyeccion de dependencias
- Retrofit para consumo HTTP
- Gson Converter para serializacion JSON
- Room para persistencia local
- KSP para procesamiento de anotaciones de Room
- Kotlin Coroutines, `StateFlow` y `Flow`
- Coil para carga de imagenes remotas

## Arquitectura

El proyecto sigue una organizacion por features con una capa comun compartida. La UI esta implementada en Compose, los estados de pantalla se exponen desde ViewModels con `StateFlow`, y la logica de acceso a datos queda separada en fuentes remotas, fuentes locales y clases de dominio.

La app combina patrones de Clean Code y separacion de responsabilidades:

- `view`: pantallas y componentes Compose.
- `viewmodel`: estado de UI, acciones del usuario y coordinacion de casos de uso.
- `domain`: orquestacion entre fuentes de datos y reglas de seleccion, por ejemplo remoto vs local.
- `model`: implementaciones de acceso a datos, servicios Retrofit y bases locales.
- `di`: definicion de modulos Koin.
- `common`: entidades, DAOs, utilidades, constantes y componentes reutilizables.
- `navigation`: rutas y `NavHost` de la aplicacion.

## Distribucion de la logica

### Entrada de la aplicacion

- `CursosANTApp` inicializa Koin y registra los modulos principales: clima, ciudades, base local, fuente remota, utilidades y componentes.
- `MainActivity` configura el tema, el `Scaffold`, la barra superior, la barra inferior y el `NavController`.
- `AppNavHost` define las rutas principales y renderiza `WeatherView` o `CitiesView`.

### Feature Clima

La feature `weather` se encarga de buscar y mostrar datos climaticos.

- `WeatherViewModel` mantiene `WeatherUiState`, ejecuta busquedas, guarda resultados y maneja mensajes de UI.
- `DataSource` coordina la fuente remota, la fuente local y `NetworkUtils`.
- `RemoteDatabase` llama a `WeatherService` usando Retrofit y transforma la respuesta con `FormatUtils`.
- `LocalDatabase` guarda y consulta entidades de Room.
- `WeatherService` define los endpoints de WeatherAPI.

Flujo principal:

1. El usuario busca una ciudad desde la pantalla de clima.
2. `WeatherViewModel` delega en `DataSource`.
3. `RemoteDatabase` consulta WeatherAPI.
4. `FormatUtils` convierte `WeatherResponse` a `WeatherCity`.
5. La UI observa el `StateFlow` y actualiza la pantalla.
6. Si se guarda el resultado, Room persiste ciudad y clima de forma relacionada.

### Feature Ciudades

La feature `cities` administra los registros almacenados.

- `CitiesViewModel` observa las ciudades guardadas en tiempo real con `Flow`.
- `cities.model.LocalDatabase` encapsula operaciones locales de listado y borrado.
- `ICitiesViewModel` define el contrato usado por la pantalla.
- `IntentUtils` abre la ubicacion de una ciudad en una aplicacion externa compatible.

Flujo principal:

1. Room emite los cambios de ciudades mediante `Flow<List<Location>>`.
2. `CitiesViewModel` actualiza `CityUiState`.
3. La UI renderiza la lista de ciudades almacenadas.
4. El usuario puede eliminar un registro o abrir su ubicacion en mapas.

## Persistencia local con Room

La base de datos se define en `AppDatabase` y contiene las entidades:

- `Location`: ciudad, pais, latitud y longitud.
- `Weather`: temperatura, descripcion, viento, icono y relacion con ciudad.

Los DAOs principales son:

- `CityDAO`: alta, actualizacion, eliminacion y consultas de ciudades.
- `WeatherDao`: operaciones sobre clima y consultas relacionadas.
- `WeatherCityDAO`: operaciones transaccionales para guardar o eliminar ciudad y clima en conjunto.

## Inyeccion de dependencias con Koin

Koin centraliza la creacion de dependencias para mantener las clases desacopladas y faciles de probar.

- `remoteDataSourceModule`: `Retrofit`, `GsonConverterFactory` y `WeatherService`.
- `localDataSourceModule`: `AppDatabase`, `CityDAO`, `WeatherDao` y `WeatherCityDAO`.
- `weatherModule`: fuentes de datos de clima, `DataSource` y `WeatherViewModel`.
- `citiesModule`: fuente local de ciudades y `CitiesViewModel`.
- `utilsModule` y `componentsModule`: utilidades y dependencias comunes.

## Ejecucion

Requisitos:

- Android Studio actualizado.
- JDK compatible con el Gradle Wrapper del proyecto.
- SDK Android 36 instalado.

Comandos utiles:

```bash
./gradlew assembleDebug
./gradlew test
```

Tambien se puede abrir el proyecto en Android Studio y ejecutar la configuracion `app`.

## Nota educativa

Este proyecto fue creado con fines educativos para practicar arquitectura Android moderna, consumo de APIs, persistencia local, inyeccion de dependencias y construccion de UI declarativa con Compose.

El uso comercial esta prohibido por la licencia del proyecto.
