import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

apply {
    from(file("android-library-config.gradle"))
    from(file("ios-target.gradle"))
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }

    dataBinding {
        isEnabled = true
    }
}

androidExtensions {
    isExperimental = true
}

kotlin {
    targets {
        val configureIos: KotlinNativeTarget.() -> Unit = {
            binaries {
                framework("MultiPlatformLibrary") {
                    freeCompilerArgs.add("-Xobjc-generics")
                }
            }
        }

        iosArm64("iosArm64", configureIos)
        iosX64("iosX64", configureIos)
        android()
    }

    sourceSets {
        val androidMain by getting {}
        val commonMain by getting {}
        val iosArm64Main by getting {
            kotlin.srcDir(file("src/iosMain/kotlin"))
        }
        val iosX64Main by getting {
            kotlin.srcDir(file("src/iosMain/kotlin"))
        }
    }
}

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)

    kaptLibrary(Deps.Kapt.androidLifecycle)
}

tasks.mapNotNull { it as? KotlinNativeLink }
    .mapNotNull { it.binary as? Framework }
    .forEach { framework ->
        val linkTask = framework.linkTask
        val syncTaskName = linkTask.name.replaceFirst("link", "sync")
        val syncFramework = tasks.create(syncTaskName, Sync::class.java) {
            group = "cocoapods"

            from(framework.outputDirectory)
            into(file("build/cocoapods/framework"))
        }
        syncFramework.dependsOn(linkTask)
    }
