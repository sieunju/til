import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.3" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.22")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.com") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("generateReleaseNote") {
    getReleaseNote()
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

        val version = "Version Name: ${Apps.versionName}"
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