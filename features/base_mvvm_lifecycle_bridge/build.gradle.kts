plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.features.base_mvvm_lifecycle_bridge"
}

dependencies {
    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
}