plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
}

dependencies {
    implementation(AndroidX.appCompat)
    implementation(AndroidX.ktx)
    implementation(AndroidX.lifecycle)

    /**
     * Rx
     */
    implementation(Rx.java)

    /**
     * Unit Test
     */
    androidTestImplementation(UnitTest.junit)
    testImplementation(UnitTest.junit)
    testImplementation(UnitTest.androidJUnit)
}