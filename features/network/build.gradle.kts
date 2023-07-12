plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.features.network"
    buildFeatures {
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:network-bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.activity)
    implementation(AndroidX.material)
    implementation(AndroidX.fragment)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.liveData)

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
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Rx.android)

    /**
     * Coroutines
     */
    implementation(Co.core)
    implementation(Co.android)

    /**
     * Network
     */
    implementation(Retrofit.base)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.Espresso.core)
}