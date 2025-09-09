plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.network"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.networkBridge)
    implementation(projects.features.networkV2Bridge)
    implementation(projects.features.networkErrorHandlingBridge)
    implementation(projects.features.networkJsendFormatBridge)
    implementation(projects.features.networkExpiredTokenBridge)
}