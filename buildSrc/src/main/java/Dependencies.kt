import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Apps {
    const val versionCode = 7
    const val versionName = "0.0.7"
}

object Versions {
    const val retrofit = "2.9.0"
    const val lifecycle = "2.6.1"
    const val hilt = "2.48"
}

object AndroidX {
    const val ktx = "androidx.core:core-ktx:1.8.0"
    const val appCompat = "androidx.appcompat:appcompat:1.6.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val activity = "androidx.activity:activity-ktx:1.6.1"
    const val fragment = "androidx.fragment:fragment-ktx:1.6.2"
    const val material = "com.google.android.material:material:1.9.0"
    const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
    const val viewpager = "androidx.viewpager2:viewpager2:1.0.0"
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.3.2"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}

object Kotlin {
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:1.8.22"
}

object Javax {
    const val inject = "javax.inject:javax.inject:1"
}

object Hilt {
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val compose = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object Rx {
    const val java = "io.reactivex.rxjava3:rxjava:3.1.2"
    const val android = "io.reactivex.rxjava3:rxandroid:3.0.0"
    const val kotlin = "io.reactivex.rxjava3:rxkotlin:3.0.1"
}

object Co {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
}

object Retrofit {
    const val base = "com.squareup.retrofit2:retrofit:2.9.0"
    const val rxjava = "com.squareup.retrofit2:adapter-rxjava3:2.9.0"
    const val kotlinx = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val okhttp = "com.squareup.okhttp3:okhttp:4.12.0"
    const val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:4.12.0"
}

object Glide {
    const val base = "com.github.bumptech.glide:glide:4.13.2"
    const val okhttp = "com.github.bumptech.glide:okhttp3-integration:4.13.2"
    const val compiler = "com.github.bumptech.glide:compiler:4.13.2"
    const val annotations = "com.github.bumptech.glide:annotations:4.13.2"
    const val compose = "com.github.bumptech.glide:compose:1.0.0-beta01"
//    const val base = "com.github.bumptech.glide:glide:4.16.0"
//    const val okhttp = "com.github.bumptech.glide:okhttp3-integration:4.16.0"
//    const val compiler = "com.github.bumptech.glide:ksp:4.16.0"
//    const val annotations = "com.github.bumptech.glide:annotations:4.16.0"
//    const val compose = "com.github.bumptech.glide:compose:1.0.0-beta01"
//    const val base = "com.github.bumptech.glide:glide:5.0.0-rc01"
//    const val okhttp = "com.github.bumptech.glide:okhttp3-integration:5.0.0-rc01"
//    const val compiler = "com.github.bumptech.glide:ksp:5.0.0-rc01"
//    const val annotations = "com.github.bumptech.glide:annotations:5.0.0-rc01"
//    const val compose = "com.github.bumptech.glide:compose:1.0.0-beta01"
}

object KotlinX {
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1"
}

object Log {
    const val timber = "com.jakewharton.timber:timber:5.0.1"
}

object HttpTracking {
    const val interceptor = "com.github.sieunju.httptracking:interceptor:1.3.2"
    const val ui = "com.github.sieunju.httptracking:ui:1.3.2"
}

object Compose {
    const val compile = "1.4.8"
    const val base = "androidx.compose:compose-bom:2024.02.02"
    const val material = "androidx.compose.material3:material3"
    const val ui = "androidx.compose.ui:ui"
    const val preview = "androidx.compose.ui:ui-tooling-preview"
    const val liveData = "androidx.compose.runtime:runtime-livedata"
    const val activity = "androidx.activity:activity-compose:1.5.1"
    const val tracing = "androidx.compose.runtime:runtime-tracing:1.0.0-beta01"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    const val runtime = "androidx.compose.runtime:runtime"
    const val navigation = "androidx.navigation:navigation-compose:2.7.7"
    const val navigationViewModel = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object Firebase {
    const val bom = "com.google.firebase:firebase-bom:33.1.0"
    const val analytics = "com.google.firebase:firebase-analytics"
    const val crash = "com.google.firebase:firebase-crashlytics-ktx"
    operator fun invoke(scope: DependencyHandlerScope) {
        scope.add("implementation", scope.platform(bom))
        scope.implementation(analytics, crash)
    }
}

object UnitTest {
    const val junit = "junit:junit:4.13.2"
    const val rules = "androidx.test:rules:1.5.0"
    const val room = "androidx.room:room-testing:2.3.0"
    const val core = "androidx.test:core-ktx:1.5.0"
    const val archCore = "androidx.arch.core:core-testing:2.1.0"
    const val androidJUnit = "androidx.test.ext:junit-ktx:1.1.5"
    const val runner = "androidx.test:runner:1.5.2"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"

    object Espresso {
        const val core = "androidx.test.espresso:espresso-core:3.5.1"
        const val intents = "androidx.test.espresso:espresso-intents:3.5.1"
    }

    object Hilt {
        const val base = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Compose {
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val junit = "androidx.compose.ui:ui-test-junit4"
        const val manifest = "androidx.compose.ui:ui-test-manifest"
    }
}

private fun DependencyHandler.kapt(
    vararg list: String
) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

private fun DependencyHandler.implementation(
    vararg list: String
) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

private fun DependencyHandler.debugImplementation(
    vararg list: String
) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}

private fun DependencyHandler.androidTestImplementation(
    vararg list: String
) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

private fun DependencyHandler.testImplementation(
    vararg list: String
) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
