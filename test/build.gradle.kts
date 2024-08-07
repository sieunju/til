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
    androidTestImplementation(project(":legacy"))
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
    androidTestImplementation(project(":features:rv_simple_like"))
    androidTestImplementation(project(":features:rv_simple_like_bridge"))
    androidTestImplementation(project(":features:rv_diff_util_performance"))
    androidTestImplementation(project(":features:rv_diff_util_performance_bridge"))
    androidTestImplementation(project(":features:rv_refactor_diff_util"))
    androidTestImplementation(project(":features:rv_refactor_diff_util_bridge"))
    androidTestImplementation(project(":features:rv_diff_util_2"))
    androidTestImplementation(project(":features:rv_diff_util_2_bridge"))
    androidTestImplementation(project(":features:base_mvvm_lifecycle"))
    androidTestImplementation(project(":features:base_mvvm_lifecycle_bridge"))
    androidTestImplementation(project(":features:base_mvvm_bottom_sheet"))
    androidTestImplementation(project(":features:base_mvvm_bottom_sheet_bridge"))
    androidTestImplementation(project(":features:compose_permissions_result"))
    androidTestImplementation(project(":features:compose_permissions_result_bridge"))
    androidTestImplementation(project(":features:compose_navigation"))
    androidTestImplementation(project(":features:compose_navigation_bridge"))

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
    testImplementation("com.google.code.gson:gson:2.10.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

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