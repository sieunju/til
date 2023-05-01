plugins {
    id("com.android.application")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
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
        dataBinding = true
    }
}

dependencies {
    implementation(project(":core"))

    implementation(project(":features:main"))
    implementation(project(":features:network-bridge"))
    implementation(project(":features:network"))
    implementation(project(":features:recyclerview-bridge"))
    implementation(project(":features:recyclerview"))
    implementation(project(":features:base-mvvm-bridge"))
    implementation(project(":features:base-mvvm"))
    implementation(project(":features:async_migrate"))
    implementation(project(":features:async_migrate_bridge"))

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
    implementation(AndroidX.multidex)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)
    implementation(Kotlin.stdLib)

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