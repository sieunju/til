plugins {
    id("til.feature")
}

android {
    namespace = "com.features.compose_navigation"
}

dependencies {
    implementation(projects.core)
    implementation(projects.coreNavigator)
    implementation(projects.features.composeNavigationBridge)

    implementation(libs.androidx.appcompat)
    implementation(libs.retrofit)
}