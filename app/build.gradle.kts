import expr.dependencies.addLogicDependencies
import expr.dependencies.addUiDependencies
import expr.dependencies.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.effective.application"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.effective.application"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

addLogicDependencies()
addUiDependencies()

dependencies {

    implementation(project(":core:utils"))
    implementation(project(":core:navigation"))
    implementation(project(":core:common"))
    implementation(project(":core:android"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    implementation(project(":feature:bottom_menu"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:vacancy"))
    implementation(project(":feature:response"))

    implementation(project(":data:vacancies"))

    implementation(project(":domain:general"))

    implementation(project(":resources"))
}

