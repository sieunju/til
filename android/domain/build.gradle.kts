plugins {
    id("java-library")
    id("kotlin")
    id("kotlinx-serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":model"))

    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(KotlinX.serialization)
}