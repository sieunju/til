plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.hmju.compose_permissions_result_bridge"
}

dependencies {
    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
}