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

/**
 * 버전명을 버전 코드로 변환
 * 형식: Major.Minor.Patch (예: 1.1.10)
 * 변환: Major * 1000000 + Minor * 1000 + Patch (예: 1.1.10 -> 1001010)
 */
fun String.toVersionCode(): Int {
    val parts = this.split(".")
    require(parts.size == 3) { "Version name must be in format Major.Minor.Patch (e.g., 1.1.10)" }
    val major = parts[0].toInt()
    val minor = parts[1].toInt()
    val patch = parts[2].toInt()
    require(major in 0 until 1000) { "Major version must be between 0 and 999" }
    require(minor in 0 until 1000) { "Minor version must be between 0 and 999" }
    require(patch in 0 until 1000) { "Patch version must be between 0 and 999" }
    return major * 1000000 + minor * 1000 + patch
}

android {
    namespace = "com.hmju.til"
    compileSdk = 36
    defaultConfig {
        minSdk = 28
        targetSdk = 36
        applicationId = "com.hmju.til"
        versionCode = libs.versions.appVersion.get().toVersionCode()
        versionName = libs.versions.appVersion.get()
        setProperty("archivesBaseName", "til_${versionName}_${versionCode}")

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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(projects.coreNavigator)
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
    implementation(projects.features.activityResult)
    implementation(projects.features.fragmentNavigation)

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