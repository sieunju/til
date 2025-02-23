import com.hmju.til.configCoroutineAndroid
import com.hmju.til.configHiltAndroid

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