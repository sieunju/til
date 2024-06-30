plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.features.rv_refactor_diff_util_bridge"
}

dependencies {
    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
}