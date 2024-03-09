plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.features.recyclerview"
    dataBinding { enable = true }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":legacy"))
    implementation(project(":features:recyclerview-bridge"))
    implementation(project(":features:recyclerview_custom_paging_bridge"))
    implementation(project(":features:rv_simple_like_bridge"))
    implementation(project(":features:rv_diff_util_performance_bridge"))
    implementation(project(":features:rv_refactor_diff_util_bridge"))

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
    kapt(Hilt.compiler)

    /**
     * Rx
     */
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Rx.android)

    /**
     * Network
     */
    implementation(Retrofit.base)

    /**
     * Kotlin
     */
    implementation(Kotlin.reflect)

    /**
     * Glide
     */
    implementation(Glide.base)

    /**
     * Coroutines
     */
    implementation(Co.core)
    implementation(Co.android)

    /**
     * Kotlinx Serialization
     */
    implementation(KotlinX.serialization)

    /**
     * Unit Test
     */
    testImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.junit)
    androidTestImplementation(UnitTest.androidJUnit)
    androidTestImplementation(UnitTest.Espresso.core)
}