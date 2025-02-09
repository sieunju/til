plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.features.compose_ui"
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
    dataBinding { enable = true }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compile
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:compose-ui-bridge"))

    implementation(AndroidX.appCompat)
    implementation(AndroidX.activity)
    implementation(AndroidX.fragment)
    implementation(AndroidX.constraintLayout)

    /**
     * Network
     */
    implementation(Retrofit.base)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * Coroutines
     */
    implementation(Co.core)
    implementation(Co.android)

    /**
     * Compose
     */
    implementation(platform(Compose.base))
    implementation(Compose.material)
    implementation(Compose.ui)
    implementation(Compose.preview)
    implementation(Compose.viewModel)
    implementation(Compose.constraint)
    implementation(Compose.tracing)
    implementation(Compose.activity)

    testImplementation(UnitTest.junit)
    androidTestImplementation(platform(Compose.base))
    debugImplementation(UnitTest.Compose.tooling)
    androidTestImplementation(UnitTest.Compose.junit)
    androidTestImplementation(UnitTest.Compose.manifest)
}