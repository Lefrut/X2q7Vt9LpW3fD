package expr.dependencies

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.getByType

fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)


fun DependencyHandler.ksp(dependencyNotation: String): Dependency? =
    add("ksp", dependencyNotation)


fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)


fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")


fun Project.implement(aliases: String) {
    try {
        dependencies.implementation(libs.findLibrary(aliases).get().get().toString())
    } catch (_: Throwable) {
    }
}

fun Project.ksp(aliases: String) {
    try {
        dependencies.ksp(libs.findLibrary(aliases).get().get().toString())
    } catch (_: Throwable) {
    }
}


fun Project.addLogicDependencies() {
    libs.apply {
        implement("okhttp")
        implement("okhttp-logging")
        implement("retrofit")
        implement("retrofit-json")
        implement("adapter-delegates")
        implement("adapter-delegates-viewbinding")
        implement("kotlin-stdlib")
        implement("kotlinx-coroutines-core")
        implement("kotlinx-coroutines-android")
        implement("hilt-android")
        ksp("hilt-compiler")
        implement("room-runtime")
        implement("room-ktx")
        ksp("room-compiler")
        implement("lifecycle-viewmodel-ktx")
        implement("lifecycle-runtime-ktx")

    }

}

fun Project.addUiDependencies() = libs.apply {
    implement("androidx-appcompat")
    implement("androidx-core-ktx")
    implement("androidx-activity")
    implement("material")
    implement("androidx-constraintlayout")
}



