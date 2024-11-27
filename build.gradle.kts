// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }  // JitPack repository for external dependencies
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.5.0")  // Use appropriate version of Gradle plugin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")  // Use appropriate version of Kotlin plugin
    }
}

