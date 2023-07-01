plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.features.base_mvvm_bridge"
}

dependencies {

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)
}