plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.features.network"
    dataBinding { enable = true }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:network-bridge"))
    implementation(project(":features:network_v2-bridge"))
    implementation(project(":features:network_error_handling_bridge"))
    implementation(project(":features:network_jsend_format_bridge"))
    implementation(project(":features:network_expired_token_bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
    implementation(AndroidX.fragment)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.Espresso.core)
}