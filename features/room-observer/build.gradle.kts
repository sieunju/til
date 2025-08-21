plugins {
    id("til.feature")
    id("til.library")
    id("til.androidx")
}

android {
    namespace = "com.features.room_observer"
}

dependencies {
    implementation(projects.features.roomObserverBridge)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.rx)
    implementation(libs.compose.livedata)
    implementation(libs.retrofit)
    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.kotlinx.datetime)
}