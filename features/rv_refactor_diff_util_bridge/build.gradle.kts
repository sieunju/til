plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.features.rv_refactor_diff_util_bridge"
    compileSdk = Apps.targetSdk
    defaultConfig {
        minSdk = Apps.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
}