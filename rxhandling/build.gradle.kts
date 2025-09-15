plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.til.rxhandling"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.retrofit.rxjava)
    implementation(libs.retrofit.json)
    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(kotlin("reflect"))
}