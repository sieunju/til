plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.hmju.test"
    defaultConfig {
        testInstrumentationRunner = "com.hmju.test.HiltTestRunner"
    }
    dataBinding { enable = true }
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
    androidTestImplementation(project(":features:network_v2"))
    androidTestImplementation(project(":features:network_v2-bridge"))
    androidTestImplementation(project(":features:compose-ui"))
    androidTestImplementation(project(":features:compose-ui-bridge"))
    androidTestImplementation(project(":features:rv_custom_paging"))
    androidTestImplementation(project(":features:rv_custom_paging_bridge"))
    androidTestImplementation(project(":features:network_error_handling"))
    androidTestImplementation(project(":features:network_error_handling_bridge"))
    androidTestImplementation(project(":features:network_jsend_format"))
    androidTestImplementation(project(":features:network_jsend_format_bridge"))
    androidTestImplementation(project(":features:network_expired_token"))
    androidTestImplementation(project(":features:network_expired_token_bridge"))

    /**
     * Android X
     */
    implementation(AndroidX.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.material)
    implementation(AndroidX.activity)
    implementation(AndroidX.fragment)
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