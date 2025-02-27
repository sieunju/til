plugins {
    id("til.feature")
}

android {
    namespace = "com.hmju.compose_permissions_result"
}

dependencies {
    implementation(projects.features.composePermissionsResultBridge)
    implementation(libs.androidx.appcompat)
}