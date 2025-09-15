import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val properties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.hmju.til"
    compileSdk = 35
    defaultConfig {
        minSdk = 28
        targetSdk = 35
        applicationId = "com.hmju.til"
        versionCode = 1
        versionName = libs.versions.appVersion.get()
        setProperty("archivesBaseName", "til_${versionCode}_${versionName}")

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

dependencies {
    implementation(projects.core)
    implementation(projects.legacy)
    implementation(projects.features.main)
    implementation(projects.features.network)
    implementation(projects.features.recyclerview)
    implementation(projects.features.baseMvvm)
    implementation(projects.features.asyncMigrate)
    implementation(projects.features.networkV2)
    implementation(projects.features.composeUi)
    implementation(projects.features.rvCustomPaging)
    implementation(projects.features.networkErrorHandling)
    implementation(projects.features.networkJsendFormat)
    implementation(projects.features.networkExpiredToken)
    implementation(projects.features.rvSimpleLike)
    implementation(projects.features.rvDiffUtilPerformance)
    implementation(projects.features.rvRefactorDiffUtil)
    implementation(projects.features.rvDiffUtil2)
    implementation(projects.features.baseMvvmLifecycle)
    implementation(projects.features.baseMvvmBottomSheet)
    implementation(projects.features.composePermissionsResult)
    implementation(projects.features.composeNavigation)
    implementation(projects.features.roomObserver)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)
    implementation(libs.retrofit.rxjava)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.android.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.kotlinx.json)

    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)

    implementation(libs.timber)
    implementation(libs.httptracking.ui)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crash)

    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.junit)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.ext)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(projects.test)
}