plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.rv_simple_like"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.rvSimpleLikeBridge)

    implementation(libs.rx.java)
    implementation(libs.rx.android)
    implementation(libs.retrofit)
    implementation(libs.glide)
}