# Agent Guide: newsAndroid

This file provides context and guidelines for AI agents working on the **newsAndroid** project.

## Project Overview
A modern Android application for news, built with Jetpack Compose and following modern Android development practices. This is a mono-repository managed by Gradle.

## Tech Stack
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material 3)
- **Architecture:** MVVM / MVI
- **Build System:** Gradle Kotlin DSL (with Version Catalogs)
- **Concurrency:** Kotlin Coroutines & Flow
- **Dependency Injection:** Hilt

## Development Commands
- **Build:** `./gradlew assembleDebug`
- **Clean:** `./gradlew clean`
- **Run Unit Tests:** `./gradlew test`
- **Run Instrumented Tests:** `./gradlew connectedAndroidTest`
- **Lint:** `./gradlew lint`

## Architecture & Design Patterns
- **UI Layer:** Use Composable functions. Prefer stateless composables by hoisting state.
- **ViewModel:** Use `androidx.lifecycle.ViewModel` for managing UI state and business logic.
- **Data Layer:** Use Repositories to abstract data sources (Network, Database).
- **Navigation:** Use Compose Navigation.
    - Prefer **Type-safe Navigation** with Kotlin Serialization.
    - Define routes as `@Serializable` objects or classes.
    - Hoist navigation logic by passing lambdas (e.g., `onItemClick: (String) -> Unit`) to Composables rather than passing `NavController`.
    - Avoid passing large data objects through navigation routes; pass unique IDs and fetch data in the destination ViewModel.
- **Dependency Injection:** Use Hilt for DI. Annotate ViewModels with `@HiltViewModel`.

## Coding Guidelines
- **Kotlin Style:** Follow official [Kotlin Style Guide](https://kotlinlang.org/docs/coding-conventions.html).
- **Compose Best Practices:**
    - Use `remember` and `rememberSaveable` appropriately.
    - Avoid heavy computations in Composables.
    - Use `Modifier` as the first optional parameter in all UI Composables.
- **Naming:**
    - Composables should be PascalCase and nouns.
    - Functions that return `Unit` should start with a verb (except Composables).
- **Resources:** Use `res/values/strings.xml` for all user-facing text to support localization.

## Testing Standards
- **Unit Tests:** Place in `src/test/java`. Use JUnit 4/5 and MockK/Mockito.
- **UI Tests:** Place in `src/androidTest/java`. Use Compose Test Rule.
- **Naming:** Use descriptive test names (e.g., `whenDataLoaded_thenStateIsUpdated`).

## Monorepo Strategy
- Keep feature-specific logic within its own module (if applicable).
- Shared logic/UI should reside in a `:core` or `:ui-common` module.
- Always check `gradle/libs.versions.toml` before adding new dependencies.
