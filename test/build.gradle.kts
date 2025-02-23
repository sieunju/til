plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

android {
    namespace = "com.hmju.test"
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

    androidTestImplementation(libs.androidx.lifecycle.runtime)
    androidTestImplementation(libs.androidx.lifecycle.viewmodel)
    androidTestImplementation(libs.androidx.lifecycle.livedata)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android)
    kspAndroidTest(libs.hilt.compiler)

    androidTestImplementation(libs.retrofit)
    androidTestImplementation(libs.okhttp)
    androidTestImplementation(libs.retrofit.rxjava)
    androidTestImplementation(libs.retrofit.kotlinx)
    androidTestImplementation(libs.okhttp.interceptor)
    testImplementation("com.google.code.gson:gson:2.11.0")
    testImplementation(libs.kotlinx.datetime)
}