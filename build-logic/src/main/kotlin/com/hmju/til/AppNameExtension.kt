package com.hmju.til

import org.gradle.api.Project

/**
 * Setter NameSpace
 */
fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "hmju.til.$name"
    }
}