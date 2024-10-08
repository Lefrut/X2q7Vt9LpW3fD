import expr.dependencies.addLogicDependencies
import expr.dependencies.addUiDependencies

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.effective.login"
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
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures.viewBinding = true
}

addLogicDependencies()
addUiDependencies()

dependencies {
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.otpview)

    implementation(project(":domain:general"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:utils"))
    implementation(project(":resources"))
}