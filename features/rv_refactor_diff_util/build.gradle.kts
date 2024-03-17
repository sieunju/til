plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.features.rv_refactor_diff_util"
    dataBinding { enable = true }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:rv_refactor_diff_util_bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.fragment)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.material)
    implementation(AndroidX.cardView)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * Coroutines
     */
    implementation(Co.core)
    implementation(Co.android)

    /**
     * Network
     */
    implementation(Retrofit.base)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Glide
     */
    implementation(Glide.base)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.Espresso.core)
}