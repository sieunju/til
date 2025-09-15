import java.io.FileInputStream
import java.util.Properties
plugins {
    id("til.library")
    id("til.compose")
    id("til.androidx")
    id("kotlin-kapt") // DataBinding 대응
}

val properties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    namespace = "com.hmju.core"
    defaultConfig {
        buildConfigField("String", "BASE_URL", properties.getProperty("base_url"))
        buildConfigField("String", "AUTH_TYPE", properties.getProperty("auth_type"))
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.glide)
    implementation(libs.glide.compiler)
    implementation(libs.glide.okhttp3)
    implementation(libs.glide.annotations)
    implementation(libs.glide.compose)
    ksp(libs.glide.compiler)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.retrofit.rxjava)
    implementation(libs.retrofit.json)
    implementation(libs.httptracking.interceptor)
    implementation(libs.okhttp.interceptor)

    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.rx.android)
    implementation(libs.kotlin.reflect)

    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    implementation(libs.room.runtime)
    implementation(libs.room.rx)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.kotlinx.datetime)
}