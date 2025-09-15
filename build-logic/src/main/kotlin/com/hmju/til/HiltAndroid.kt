package com.hmju.til

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configHiltAndroid() {
    with(pluginManager) {
        apply("dagger.hilt.android.plugin")
        apply("com.google.devtools.ksp")
    }

    val libs = extensions.libs
    dependencies {
        add("implementation",libs.findLibrary("hilt.android").get())
        add("ksp",libs.findLibrary("hilt.compiler").get())
        add("kspAndroidTest",libs.findLibrary("hilt.compiler").get())
    }
}

internal class HiltAndroidPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configHiltAndroid()
        }
    }
}