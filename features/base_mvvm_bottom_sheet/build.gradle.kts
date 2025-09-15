plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.base_mvvm_bottom_sheet"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.baseMvvmBottomSheetBridge)
    implementation(libs.retrofit)
}