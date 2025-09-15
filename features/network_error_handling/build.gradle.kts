plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.network_error_handling"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.networkErrorHandlingBridge)

    implementation(libs.retrofit)
}