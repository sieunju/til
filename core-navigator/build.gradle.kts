plugins {
    id("til.library")
    id("til.compose")
    id("til.androidx")
}

android {
    namespace = "com.hmju.core_navigator"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.coroutine)
    implementation(libs.coroutine.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}