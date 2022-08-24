plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    kotlin("android")
    kotlin("kapt")
}

android {
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

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)

    implementation(Log.timber)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    testImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.rules)
    androidTestImplementation(UnitTest.runner)
    implementation(kotlin("reflect"))
}