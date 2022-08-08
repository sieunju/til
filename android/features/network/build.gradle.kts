plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    defaultConfig {
        // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.features.network.HiltNetworkTestRunner"
    }
    buildFeatures {
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":features:network-requirements"))

    implementation(project(":model"))
    implementation(project(":domain"))
    implementation(project(":lifecycle"))
    implementation(project(":loginmanager"))

    implementation(project(":features:core"))
    implementation(project(":features:core-ui"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.activity)
    implementation(AndroidX.material)
    implementation(AndroidX.fragment)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.liveData)

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
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.archCore)

    androidTestImplementation(UnitTest.Hilt.base)
    kaptAndroidTest(UnitTest.Hilt.compiler)

    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.core)
    androidTestImplementation(UnitTest.ext)
    androidTestImplementation(UnitTest.rules)
    androidTestImplementation(UnitTest.Espresso.core)

    androidTestApi(project(":data"))
    androidTestApi(project(":rxhandling"))

    /**
     * Network
     */
    androidTestImplementation(Retrofit.base)
    androidTestImplementation(Retrofit.okhttp)
    androidTestImplementation(Retrofit.rxjava)
    androidTestImplementation(Retrofit.kotlinx)
    androidTestImplementation(Retrofit.okhttpLogger)
}