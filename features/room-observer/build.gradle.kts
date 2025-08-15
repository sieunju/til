plugins {
    id("til.feature")
}

android {
    namespace = "com.features.room_observer"
}

dependencies {
    implementation(projects.features.roomObserverBridge)

    implementation(libs.androidx.appcompat)
    implementation(libs.retrofit)
    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(projects.features.composeUiBridge)
}