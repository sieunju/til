plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.features.network_bridge"
}

dependencies {
    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
}