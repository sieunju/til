plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.rv_diff_util_2"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.rvDiffUtil2Bridge)

    implementation(libs.retrofit)
    implementation(libs.glide)
}