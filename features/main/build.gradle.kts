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
    implementation(projects.features.recyclerviewBridge)
    implementation(projects.features.networkBridge)
    implementation(projects.features.baseMvvmBridge)
    implementation(projects.features.asyncMigrateBridge)
    implementation(projects.features.composeUiBridge)
    implementation(projects.features.composePermissionsResultBridge)
    implementation(projects.features.composeNavigationBridge)
    implementation(projects.features.roomObserverBridge)
}