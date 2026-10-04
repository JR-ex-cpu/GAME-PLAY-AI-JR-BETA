# GAME PLAY AI JR — BETA

Proyecto Android independiente para crear un agente de automatización con AccessibilityService, aprendizaje de rutinas y verificación de resultados.

## Objetivo inicial

Construir una base técnica real y compilable para:

- Android + Kotlin
- MainActivity
- AccessibilityService
- configuración de accesibilidad
- detección de eventos
- almacenamiento temporal de eventos
- estado del servicio
- botón de parada
- documentación y CI

## Estructura

- `app/` módulo de la aplicación Android
- `MainActivity.kt` pantalla principal
- `GamePlayAccessibilityService.kt` servicio de accesibilidad
- `.github/workflows/android-build.yml` compilación automática

## Requisitos

- JDK 17
- Android SDK 34
- Gradle 8.7+

## Compilar localmente

```bash
gradle assembleDebug
```

## Flujo de trabajo

1. Activar el servicio de accesibilidad desde la app.
2. Abrir una aplicación objetivo.
3. Registrar eventos de accesibilidad.
4. Preparar la base para rutinas futuras.

## Estado

Versión base: 0.1.0-beta
