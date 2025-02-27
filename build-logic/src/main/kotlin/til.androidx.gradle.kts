import com.hmju.til.libs

plugins {
    id("com.android.library")
}

android {
    dataBinding { enable = true }
}

dependencies {
    val libs = project.extensions.libs
    implementation(libs.findLibrary("androidx.core.ktx").get())
    implementation(libs.findLibrary("androidx.appcompat").get())
    implementation(libs.findLibrary("androidx.constraintlayout").get())
    implementation(libs.findLibrary("androidx.activity").get())
    implementation(libs.findLibrary("androidx.fragment").get())
    implementation(libs.findLibrary("android.material").get())
    implementation(libs.findLibrary("androidx.viewpager").get())
    implementation(libs.findLibrary("androidx.cardview").get())
    implementation(libs.findLibrary("androidx.recyclerview").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtime").get())
    implementation(libs.findLibrary("androidx.lifecycle.viewmodel").get())
    implementation(libs.findLibrary("androidx.lifecycle.livedata").get())
}