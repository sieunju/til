plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":lifecycle"))

    /**
     * Android X
     */
    implementation(AndroidX.viewModel)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.activity)
    implementation(AndroidX.fragment)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.liveData)
    implementation(AndroidX.material)

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
     * Timber
     */
    implementation(Log.timber)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.ext)
    androidTestImplementation(UnitTest.Espresso.core)

    androidTestApi(project(":data"))
}