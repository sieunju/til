plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.features.compose_navigation"
    buildFeatures { compose = true }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compile
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:compose_navigation_bridge"))

    implementation(AndroidX.appCompat)

    /**
     * Network
     */
    implementation(Retrofit.base)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

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
    implementation(Compose.navigation)
    implementation(Glide.compose)
}