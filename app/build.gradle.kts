import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("kapt")
}

val properties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.hmju.til"
    compileSdk = Apps.targetSdk

    defaultConfig {
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        applicationId = "com.hmju.til"
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        setProperty("archivesBaseName", "til_${versionCode}_${versionName}")
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    bundle {
        language { enableSplit = true }
        density { enableSplit = true }
        abi { enableSplit = true }
    }

    signingConfigs {
        create("release") {
            storeFile = file(properties.getProperty("keystore.release.file_path"))
            storePassword = properties.getProperty("keystore.release.store_password")
            keyAlias = properties.getProperty("keystore.release.key_alias")
            keyPassword = properties.getProperty("keystore.release.key_password")
        }
    }

    buildTypes {

        debug {
            isDebuggable = true
            isMinifyEnabled = false
            applicationIdSuffix = ".dev"
            manifestPlaceholders["appName"] = "til_dev"
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_dev"
        }

        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            manifestPlaceholders["appName"] = "til"
            manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher"
            signingConfig = signingConfigs["release"]
        }
    }

    buildFeatures {
        dataBinding { enable = true }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.register("getVersionCode") {
    doFirst {
        println("${android.defaultConfig.versionCode}")
    }
}

tasks.register("getVersionName") {
    doFirst {
        println("${android.defaultConfig.versionName}")
    }
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
    implementation(project(":features:compose_navigation"))

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
     * Timber
     */
    implementation(Log.timber)

    /**
     * HttpTracking
     */
    implementation(HttpTracking.ui)

    /**
     * Firebase SDK
     */
    Firebase(this)

    androidTestImplementation(UnitTest.runner)
    androidTestImplementation(UnitTest.junit)
    testImplementation(UnitTest.junit)
    testImplementation(UnitTest.androidJUnit)

    androidTestImplementation(project(":test"))
}