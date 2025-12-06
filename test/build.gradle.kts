plugins {
    id("til.library")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
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
    androidTestImplementation(projects.core)
    androidTestImplementation(projects.coreNavigator)
    androidTestImplementation(projects.legacy)
    androidTestImplementation(projects.features.baseMvvm)
    androidTestImplementation(projects.features.main)
    androidTestImplementation(projects.features.network)
    androidTestImplementation(projects.features.recyclerview)
    androidTestImplementation(projects.features.asyncMigrate)
    androidTestImplementation(projects.features.networkV2)
    androidTestImplementation(projects.features.composeUi)
    androidTestImplementation(projects.features.rvCustomPaging)
    androidTestImplementation(projects.features.networkErrorHandling)
    androidTestImplementation(projects.features.networkJsendFormat)
    androidTestImplementation(projects.features.networkExpiredToken)
    androidTestImplementation(projects.features.rvSimpleLike)
    androidTestImplementation(projects.features.rvDiffUtilPerformance)
    androidTestImplementation(projects.features.rvRefactorDiffUtil)
    androidTestImplementation(projects.features.rvRefactorDiffUtilBridge)
    androidTestImplementation(projects.features.rvDiffUtil2)
    androidTestImplementation(projects.features.rvDiffUtil2Bridge)
    androidTestImplementation(projects.features.baseMvvmLifecycle)
    androidTestImplementation(projects.features.baseMvvmBottomSheet)
    androidTestImplementation(projects.features.composePermissionsResult)
    androidTestImplementation(projects.features.composeNavigation)
    androidTestImplementation(projects.features.roomObserver)

    androidTestImplementation(libs.androidx.lifecycle.runtime)
    androidTestImplementation(libs.androidx.lifecycle.viewmodel)
    androidTestImplementation(libs.androidx.lifecycle.livedata)

    implementation(libs.hilt.android)
    implementation(libs.hilt.android.testing)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.retrofit)
    androidTestImplementation(libs.okhttp)
    androidTestImplementation(libs.retrofit.rxjava)
    androidTestImplementation(libs.retrofit.json)
    androidTestImplementation(libs.okhttp.interceptor)
    androidTestImplementation(libs.coroutine.test)
    testImplementation(libs.gson)
    testImplementation(libs.kotlinx.datetime)
    testImplementation(libs.rx.java)
    testImplementation(libs.rx.kotlin)
    testImplementation(libs.coroutine.test)
}