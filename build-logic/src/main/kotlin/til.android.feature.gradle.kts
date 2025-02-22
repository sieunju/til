import com.hmju.til.configCoroutineAndroid
import com.hmju.til.configHiltAndroid

plugins {
    id("til.android.library")
    id("til.android.compose")
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