object Apps {
    const val compileSdkVersion = 33
    const val buildToolsVersion = "33"
    const val minSdkVersion = 23
    const val targetSdkVersion = 33
    const val versionCode = 6
    const val versionName = "0.0.6"
}

object Versions {
    const val retrofit = "2.9.0"
    const val glide = "4.11.0"
    const val lifecycle = "2.5.1"
    const val hilt = "2.45"
}

object AndroidX {
    const val ktx = "androidx.core:core-ktx:1.8.0"
    const val appCompat = "androidx.appcompat:appcompat:1.5.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val activity = "androidx.activity:activity-ktx:1.5.1"
    const val fragment = "androidx.fragment:fragment-ktx:1.5.5"
    const val material = "com.google.android.material:material:1.9.0"
    const val multidex = "androidx.multidex:multidex:2.0.1"
    const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
    const val viewpager = "androidx.viewpager2:viewpager2:1.0.0"
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}

object Kotlin {
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:1.8.10"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:1.8.10"
}

object Javax {
    const val inject = "javax.inject:javax.inject:1"
}

object Hilt {
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
}

object Rx {
    const val java = "io.reactivex.rxjava3:rxjava:3.1.2"
    const val android = "io.reactivex.rxjava3:rxandroid:3.0.0"
    const val kotlin = "io.reactivex.rxjava3:rxkotlin:3.0.1"
}

object Co {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
}

object Retrofit {
    const val base = "com.squareup.retrofit2:retrofit:2.9.0"
    const val rxjava = "com.squareup.retrofit2:adapter-rxjava3:2.9.0"
    const val kotlinx = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val okhttp = "com.squareup.okhttp3:okhttp:4.9.1"
    const val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:4.9.1"
}

object Glide {
    const val base = "com.github.bumptech.glide:glide:4.11.0"
    const val okhttp = "com.github.bumptech.glide:okhttp3-integration:4.11.0"
    const val compiler = "com.github.bumptech.glide:compiler:4.11.0"
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

object UnitTest {
    const val junit = "junit:junit:4.13.2"
    const val rules = "androidx.test:rules:1.4.0"
    const val room = "androidx.room:room-testing:2.3.0"
    const val core = "androidx.test:core-ktx:1.4.0"
    const val archCore = "androidx.arch.core:core-testing:2.1.0"
    const val androidJUnit = "androidx.test.ext:junit-ktx:1.1.3"
    const val runner = "androidx.test:runner:1.4.0"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"

    object Espresso {
        const val core = "androidx.test.espresso:espresso-core:3.4.0"
        const val intents = "androidx.test.espresso:espresso-intents:3.4.0"
    }

    object Hilt {
        const val base = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }
}