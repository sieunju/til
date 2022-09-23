plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
}

dependencies {
    /**
     * Android X
     */
    implementation(AndroidX.appCompat)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)
}