import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

val properties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.hmju.core"
    compileSdk = Apps.targetSdk
    defaultConfig {
        minSdk = Apps.minSdk
        buildConfigField("String", "BASE_URL", properties.getProperty("base_url"))
        buildConfigField("String", "AUTH_TYPE", properties.getProperty("auth_type"))
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    dataBinding { enable = true }
    kapt {
        correctErrorTypes = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compile
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
    implementation(AndroidX.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.material)
    implementation(AndroidX.cardView)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.fragment)

    /**
     * Glide
     */
    implementation(Glide.base)
    implementation(Glide.compiler)
    implementation(Glide.okhttp)
    implementation(Glide.annotations)
    implementation(Glide.compose)
    kapt(Glide.compiler)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Network
     */
    implementation(Retrofit.base)
    implementation(Retrofit.okhttp)
    implementation(Retrofit.rxjava)
    implementation(Retrofit.kotlinx)
    implementation(HttpTracking.interceptor)
    implementation(Retrofit.okhttpLogger)

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Rx.android)

    /**
     * Coroutines
     */
    implementation(Co.core)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * Kotlin
     */
    implementation(Kotlin.reflect)

    /**
     * Compose
     */
    implementation(platform(Compose.base))
    implementation(Compose.material)
    implementation(Compose.preview)
    implementation(Compose.ui)
    implementation(Compose.runtime)
    implementation(Compose.navigation)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.Espresso.core)
    androidTestImplementation(platform(Compose.base))
    debugImplementation(UnitTest.Compose.tooling)
}