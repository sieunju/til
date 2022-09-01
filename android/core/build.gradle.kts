plugins {
    id("com.android.library")
    id("kotlinx-serialization")
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

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Network
     */
    implementation(Retrofit.base)
    implementation(Retrofit.okhttp)
    implementation(Retrofit.rxjava)
    implementation(Retrofit.kotlinx)
    implementation(Retrofit.okhttpLogger)

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

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