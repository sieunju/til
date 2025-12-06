plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.network"
}

dependencies {
    implementation(projects.core)
    implementation(projects.coreNavigator)
}