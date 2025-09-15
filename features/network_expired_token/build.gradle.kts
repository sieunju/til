plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.network_expired_token"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.networkExpiredTokenBridge)
    implementation(libs.retrofit)
}