plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:recyclerview-bridge"))
    implementation(project(":features:network-bridge"))
    implementation(project(":features:base-mvvm-bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.activity)

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
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Rx.android)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.Espresso.core)
}