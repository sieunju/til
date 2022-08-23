plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":model"))
    implementation(project(":domain"))
    implementation(project(":likemanager"))
    implementation(project(":features:core"))

    /**
     * Android X
     */
    implementation(AndroidX.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.material)
    implementation(AndroidX.cardView)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Rx.android)

    /**
     * Glide
     */
    implementation(Glide.base)
    implementation(Glide.compiler)
    implementation(Glide.okhttp)
    kapt(Glide.compiler)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.Espresso.core)
}