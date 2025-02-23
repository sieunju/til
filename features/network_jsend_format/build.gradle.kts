plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.network_jsend_format"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.networkJsendFormatBridge)
    implementation(libs.retrofit)
}