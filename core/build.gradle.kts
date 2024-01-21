plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.hmju.core"
    buildFeatures {
        compose = true
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compile
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