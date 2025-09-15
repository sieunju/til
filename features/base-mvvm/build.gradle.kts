plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.base_mvvm"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.baseMvvmBridge)
    implementation(projects.features.baseMvvmLifecycleBridge)
    implementation(projects.features.baseMvvmBottomSheetBridge)

    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.retrofit)
}