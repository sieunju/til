plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.recyclerview"
}

dependencies {
    implementation(projects.core)
    implementation(projects.legacy)
    implementation(projects.features.recyclerviewBridge)
    implementation(projects.features.rvCustomPagingBridge)
    implementation(projects.features.rvSimpleLikeBridge)
    implementation(projects.features.rvDiffUtilPerformanceBridge)
    implementation(projects.features.rvRefactorDiffUtilBridge)
    implementation(projects.features.rvDiffUtil2Bridge)

    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.retrofit)
    implementation(libs.kotlin.reflect)
    implementation(libs.glide)
}