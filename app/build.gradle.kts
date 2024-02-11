plugins {
    id("com.android.application")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.hmju.til"
    defaultConfig {
        applicationId = "com.hmju.til"
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        setProperty("archivesBaseName", "til_${versionCode}_${versionName}")
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding { enable = true }
    }
}

dependencies {
    implementation(project(":core"))

    implementation(project(":legacy"))
    implementation(project(":features:main"))
    implementation(project(":features:network-bridge"))
    implementation(project(":features:network"))
    implementation(project(":features:recyclerview-bridge"))
    implementation(project(":features:recyclerview"))
    implementation(project(":features:base-mvvm-bridge"))
    implementation(project(":features:base-mvvm"))
    implementation(project(":features:async_migrate"))
    implementation(project(":features:async_migrate_bridge"))
    implementation(project(":features:network_v2"))
    implementation(project(":features:network_v2-bridge"))
    implementation(project(":features:compose-ui"))
    implementation(project(":features:compose-ui-bridge"))

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

    androidTestImplementation(UnitTest.runner)
    androidTestImplementation(UnitTest.junit)
    testImplementation(UnitTest.junit)
    testImplementation(UnitTest.androidJUnit)

    androidTestImplementation(project(":test"))
}