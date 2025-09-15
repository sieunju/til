plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.rv_refactor_diff_util"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.rvRefactorDiffUtilBridge)

    implementation(libs.retrofit)
    implementation(libs.glide)
}