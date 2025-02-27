plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.network_v2"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.networkV2Bridge)

    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.retrofit)
}