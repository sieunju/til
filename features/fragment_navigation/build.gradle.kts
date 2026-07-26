plugins {
    id("til.feature")
}

android {
    namespace = "com.features.fragment_navigation"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.coreNavigator)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.material)
}
