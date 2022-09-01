plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.hmju.test.HiltTestRunner"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    androidTestImplementation(project(":core"))
    androidTestImplementation(project(":features:base-mvvm"))
    androidTestImplementation(project(":features:base-mvvm-requirements"))
    androidTestImplementation(project(":features:core-ui"))
    androidTestImplementation(project(":features:main"))
    androidTestImplementation(project(":features:network"))
    androidTestImplementation(project(":features:network-requirements"))
    androidTestImplementation(project(":features:recyclerview"))
    androidTestImplementation(project(":features:recyclerview-requirements"))

    /**
     * Android X
     */
    implementation(AndroidX.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.material)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)
    androidTestImplementation(UnitTest.Hilt.base)
    kaptAndroidTest(UnitTest.Hilt.compiler)

    /**
     * Network
     */
    androidTestImplementation(Retrofit.base)
    androidTestImplementation(Retrofit.okhttp)
    androidTestImplementation(Retrofit.rxjava)
    androidTestImplementation(Retrofit.kotlinx)
    androidTestImplementation(Retrofit.okhttpLogger)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.archCore)
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.core)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.rules)
    androidTestImplementation(UnitTest.Espresso.core)
}