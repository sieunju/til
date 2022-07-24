plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":features:recyclerview-requirements"))

    implementation(project(":lifecycle"))
    implementation(project(":model"))
    implementation(project(":domain"))
    implementation(project(":rxbus"))
    implementation(project(":likemanager"))
    implementation(project(":features:core"))
    implementation(project(":features:core-ui"))

    /**
     * Android X
     */
    implementation(AndroidX.appCompat)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.fragment)
    implementation(AndroidX.activity)
    implementation(AndroidX.cardView)
    implementation(AndroidX.material)

    /**
     * Timber
     */
    implementation(Log.timber)

    /**
     * Hilt
     */
    implementation(Hilt.android)
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    kapt(Hilt.compiler)

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Rx.android)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.ext)
    androidTestImplementation(UnitTest.Espresso.core)
}