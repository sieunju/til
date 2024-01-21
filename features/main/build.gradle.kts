plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.features.main"
    defaultConfig {
        testInstrumentationRunner = "com.features.main.HiltTestRunner"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:recyclerview-bridge"))
    implementation(project(":features:network-bridge"))
    implementation(project(":features:base-mvvm-bridge"))
    implementation(project(":features:async_migrate_bridge"))
    implementation(project(":features:compose-ui-bridge"))

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
    androidTestImplementation(UnitTest.Hilt.base)
    kaptAndroidTest(UnitTest.Hilt.compiler)

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Rx.android)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.archCore)
    testImplementation(UnitTest.junit)
    testImplementation(UnitTest.coroutine)
    androidTestImplementation(UnitTest.coroutine)
    testImplementation(Rx.java)
    testImplementation(Rx.kotlin)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.core)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.rules)
    androidTestImplementation(UnitTest.Espresso.core)

    androidTestImplementation(project(":core"))
    androidTestImplementation(project(":features:recyclerview"))
    androidTestImplementation(project(":features:network"))
    androidTestImplementation(project(":features:base-mvvm"))
    androidTestImplementation(project(":features:async_migrate"))
    androidTestImplementation(project(":features:compose-ui"))
}