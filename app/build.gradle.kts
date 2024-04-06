plugins {
    id("com.android.application")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.hmju.til"

    bundle {
        language { enableSplit = true }
        density { enableSplit = true }
        abi { enableSplit = true }
    }

    defaultConfig {
        applicationId = "com.hmju.til"
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        setProperty("archivesBaseName", "til_${versionCode}_${versionName}")
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug {
            applicationIdSuffix = ".dev"
            manifestPlaceholders["appName"] = "til_dev"
        }

        release {
            isShrinkResources = true
            manifestPlaceholders["appName"] = "til"
        }
    }

    buildFeatures {
        dataBinding { enable = true }
    }
}

// Release .apk Build
tasks.register("release") {
    dependsOn(tasks["clean"])
    dependsOn(tasks["assembleRelease"])
    mustRunAfter(tasks["clean"])
}

dependencies {
    implementation(project(":core"))

    implementation(project(":legacy"))
    implementation(project(":features:main"))
    implementation(project(":features:network"))
    implementation(project(":features:recyclerview"))
    implementation(project(":features:base-mvvm"))
    implementation(project(":features:async_migrate"))
    implementation(project(":features:network_v2"))
    implementation(project(":features:compose-ui"))
    implementation(project(":features:rv_custom_paging"))
    implementation(project(":features:network_error_handling"))
    implementation(project(":features:network_jsend_format"))
    implementation(project(":features:network_expired_token"))
    implementation(project(":features:rv_simple_like"))
    implementation(project(":features:rv_diff_util_performance"))
    implementation(project(":features:rv_refactor_diff_util"))
    implementation(project(":features:rv_diff_util_2"))
    implementation(project(":features:base_mvvm_lifecycle"))
    implementation(project(":features:base_mvvm_bottom_sheet"))
    implementation(project(":features:compose_permissions_result"))

    /**
     * Network
     */
    implementation(Retrofit.base)
    implementation(Retrofit.okhttp)
    implementation(Retrofit.okhttpLogger)
    implementation(Retrofit.rxjava)
    implementation(Retrofit.kotlinx)

    /**
     * Android X
     */
    implementation(AndroidX.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.activity)
    implementation(AndroidX.material)
    implementation(AndroidX.constraintLayout)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)

    /**
     * Glide
     */
    implementation(Glide.base)
    implementation(Glide.compiler)
    implementation(Glide.okhttp)
    kapt(Glide.compiler)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * HttpTracking
     */
    implementation(HttpTracking.ui)

    androidTestImplementation(UnitTest.runner)
    androidTestImplementation(UnitTest.junit)
    testImplementation(UnitTest.junit)
    testImplementation(UnitTest.androidJUnit)

    androidTestImplementation(project(":test"))
}