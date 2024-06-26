@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.zekony.vimostestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zekony.vimostestapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature:authentication"))
    implementation(project(":feature:products"))
    implementation(project(":feature:productInfo"))
    implementation(project(":data:registration"))
    implementation(project(":data:products"))
    implementation(project(":domain:registration"))
    implementation(project(":domain:products"))
    implementation(project(":resources"))

    implementation(libs.bundles.core)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.navigation)
}