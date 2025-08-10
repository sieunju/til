import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.test) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.google.services)
        classpath(libs.crashlytics.gradle)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

tasks.register("generateReleaseNote") {
    getReleaseNote()
}

// ./gradlew getAppVersion -PtargetModule=app
tasks.register("getAppVersion") {
    doLast {
        val targetService = project.properties["targetModule"] as? String ?: "app"
        val moduleMap = mapOf(
            "app" to "app"
        )
        val targetModule = moduleMap[targetService]
            ?: throw IllegalArgumentException("Invalid service name: $targetService")
        val targetProject = project(":$targetModule")
        val android = targetProject.extensions.getByName(
            "android"
        ) as com.android.build.gradle.BaseExtension
        val versionName = android.defaultConfig.versionName
        val versionCode = android.defaultConfig.versionCode
        println("$versionName (${versionCode})")
    }
}

fun getCommand(command: String): String {
    val os = ByteArrayOutputStream()
    exec {
        commandLine = command.split(" ")
        standardOutput = os
    }
    return String(os.toByteArray())
}

/**
 * 마지막 커밋한 메시지 가져와서 릴리즈노트에 입력 하기
 */
fun getReleaseNote() {
    // Process 'command 'git'' finished with non-zero exit value 128
    // val lastTag = getCommand("git describe --tags --abbrev=0")
    // println("Last Tag $lastTag")
    File(project.rootDir.absolutePath.plus("/appRelease"), "release_note.txt").run {
        parentFile.mkdir()
        val buildDate = "Build Date ${
            SimpleDateFormat(
                "yyyy. MM. dd E요일 HH:mm:ss",
                Locale.KOREAN
            ).format(Date())
        }"

        val version = "Version Name: ${libs.versions.appVersion.get()}"
        val branch = "Branch: ${getCommand("git rev-parse --abbrev-ref HEAD")}"
        val msg = "Message: ${getCommand("git rev-list --format=%B --max-count=1 HEAD")}"
        val author = "Author: ${getCommand("git log -1 --pretty=format:%an")}"

        printWriter().use {
            it.println(buildDate)
            it.println(version)
            it.println(branch)
            it.println(msg)
            it.println(author)
        }

        println(buildDate)
        println(version)
        println(branch)
        println(msg)
        println(author)
    }
}

apply {
    // ./gradlew projectDependencyGraph
    from("gradle/dependencyGraph.gradle")
}