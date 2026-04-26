plugins {
    id("til.feature")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.activity_result"
}

dependencies {
    implementation(projects.core)
    implementation(projects.coreNavigator)

    implementation(libs.flexbox)
    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.retrofit)
}