plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.features.main"
}

dependencies {
    implementation(projects.core)
    implementation(projects.coreNavigator)
}