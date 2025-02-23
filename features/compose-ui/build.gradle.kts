plugins {
    id("til.feature")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.compose_ui"
}

dependencies {
    implementation(projects.features.composeUiBridge)

    implementation(libs.retrofit)
    implementation(libs.androidx.constraintlayout.compose)
}