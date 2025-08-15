plugins {
    id("til.library")
}

android {
    namespace = "com.features.room_observer_bridge"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}