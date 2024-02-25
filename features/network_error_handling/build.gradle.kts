plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.features.network_error_handling"
    dataBinding { enable = true }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:network_error_handling_bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.fragment)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * Coroutines
     */
    implementation(Co.core)
    implementation(Co.android)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

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