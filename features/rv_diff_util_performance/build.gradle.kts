plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.rv_diff_util_performance"
}

dependencies {
    implementation(projects.core)
    implementation(projects.legacy)
    implementation(projects.features.rvDiffUtilPerformanceBridge)

    implementation(libs.rx.java)
    implementation(libs.rx.android)
}