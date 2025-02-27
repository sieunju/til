plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.async_migrate"
}

dependencies {
    implementation(projects.core)
    implementation(projects.features.asyncMigrateBridge)
    implementation(projects.legacy)

    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.retrofit)
}