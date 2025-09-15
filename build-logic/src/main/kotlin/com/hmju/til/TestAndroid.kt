package com.hmju.til

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


internal fun Project.configTestAndroid(){
    val libs = extensions.libs
    dependencies {
        add("testImplementation",libs.findLibrary("junit").get())
        add("androidTestImplementation",libs.findLibrary("junit").get())
        add("androidTestImplementation",libs.findLibrary("androidx.junit").get())
        add("androidTestImplementation",libs.findLibrary("androidx.test.ext").get())
        add("androidTestImplementation",libs.findLibrary("androidx.test.espresso.core").get())
        add("androidTestImplementation",libs.findLibrary("androidx.test.rules").get())
        add("androidTestImplementation",libs.findLibrary("androidx.room.testing").get())
        add("androidTestImplementation",libs.findLibrary("androidx.test.core.ktx").get())
        add("androidTestImplementation",libs.findLibrary("androidx.arch.core.testing").get())
    }
}