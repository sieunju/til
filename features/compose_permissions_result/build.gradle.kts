plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.hmju.compose_permissions_result"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compile
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:compose_permissions_result_bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)
    implementation(Hilt.compose)

    /**
     * Compose
     */
    implementation(platform(Compose.base))
    implementation(Compose.material)
    implementation(Compose.ui)
    implementation(Compose.preview)
    implementation(Compose.viewModel)
    implementation(Compose.constraint)
    implementation(Compose.tracing)
    implementation(Compose.activity)
    implementation(Glide.compose)

    /**
     * Timber
     */
    implementation(Log.timber)

    testImplementation(UnitTest.junit)
    androidTestImplementation(platform(Compose.base))
    debugImplementation(UnitTest.Compose.tooling)
    androidTestImplementation(UnitTest.Compose.junit)
    androidTestImplementation(UnitTest.Compose.manifest)
}