import com.hmju.til.androidExtension
import com.hmju.til.configCoroutineAndroid
import com.hmju.til.configHiltAndroid
import com.hmju.til.libs

plugins {
    id("til.library")
    id("til.compose")
}

android {
    packaging {
        resources {
            excludes.add("META-INF/**")
        }
    }
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configHiltAndroid()
configCoroutineAndroid()

androidExtension.apply {
    val libs = extensions.libs
    dependencies {
        add("implementation", project(":core"))
        add("implementation", libs.findLibrary("hilt.navigation.compose").get())
        add("implementation", libs.findLibrary("androidx.compose.navigation").get())
        add("androidTestImplementation", libs.findLibrary("androidx.compose.navigation.test").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
        add("implementation", libs.findLibrary("androidx.appcompat").get())
    }
}
