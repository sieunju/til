package com.hmju.til

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configCoroutineAndroid() {
    val libs = extensions.libs
    configureCoroutineKotlin()
    dependencies {
        add("implementation", libs.findLibrary("coroutine.android").get())
    }
}

internal fun Project.configureCoroutineKotlin() {
    val libs = extensions.libs
    dependencies {
        add("implementation", libs.findLibrary("coroutine").get())
        add("testImplementation", libs.findLibrary("coroutine.test").get())
    }
}
