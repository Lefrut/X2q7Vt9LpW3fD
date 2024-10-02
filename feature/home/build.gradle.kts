import expr.dependencies.addLogicDependencies
import expr.dependencies.addUiDependencies
import expr.dependencies.implementation
import org.jetbrains.kotlin.gradle.plugin.KotlinTargetHierarchy.SourceSetTree.Companion.main

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.effective.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures.viewBinding = true
}

addUiDependencies()
addLogicDependencies()

dependencies {
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(project(":domain:general"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:utils"))
    implementation(project(":resources"))
}