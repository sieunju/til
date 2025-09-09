plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.base_mvvm_lifecycle"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.baseMvvmLifecycleBridge)

    implementation(libs.retrofit)
}