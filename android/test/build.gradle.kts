plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.hmju.test"
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
    androidTestImplementation(project(":features:base-mvvm-bridge"))
    androidTestImplementation(project(":features:main"))
    androidTestImplementation(project(":features:network"))
    androidTestImplementation(project(":features:network-bridge"))
    androidTestImplementation(project(":features:recyclerview"))
    androidTestImplementation(project(":features:recyclerview-bridge"))
    androidTestImplementation(project(":features:async_migrate"))
    androidTestImplementation(project(":features:async_migrate_bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.material)
    androidTestImplementation(AndroidX.lifecycle)
    androidTestImplementation(AndroidX.viewModel)
    androidTestImplementation(AndroidX.liveData)

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
    testImplementation(UnitTest.coroutine)
    androidTestImplementation(UnitTest.coroutine)
    testImplementation(Rx.java)
    testImplementation(Rx.kotlin)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.core)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.rules)
    androidTestImplementation(UnitTest.Espresso.core)
}