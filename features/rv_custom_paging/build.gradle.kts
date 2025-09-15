plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.rv_custom_paging"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.rvCustomPagingBridge)

    implementation(libs.retrofit)
    implementation(libs.glide)
}