pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { setUrl("https://jitpack.io") }
        google()
        mavenCentral()
    }
}

rootProject.name = "X2q7Vt9LpW3fD"
include(":app")
include(":feature:login")
include(":feature:home")
include(":feature:favorite")
include(":feature:response")
include(":feature:vacancy")
include(":feature:bottom_menu")
include(":core:utils")
include(":core:common")
include(":core:android")
include(":core:ui")
include(":resources")
include(":core:navigation")
include(":data:vacancies")
include(":domain:general")
