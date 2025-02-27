plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "til.android.hilt"
            implementationClass = "com.hmju.til.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "til.kotlin.hilt"
            implementationClass = "com.hmju.til.HiltKotlinPlugin"
        }
    }
}