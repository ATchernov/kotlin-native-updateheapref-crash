object Deps {
    object Plugins {
        const val android =
            "com.android.tools.build:gradle:${Versions.Plugins.android}"
        const val kotlin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugins.kotlin}"
        const val serialization =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.Plugins.serialization}"
        const val androidExtensions =
            "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.Plugins.androidExtensions}"
        const val fabric =
            "io.fabric.tools:gradle:${Versions.Plugins.fabric}"
    }

    object Libs {
        object Android {
            const val kotlinStdLib =
                "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val crashlytics =
                "com.crashlytics.sdk.android:crashlytics:${Versions.Libs.Android.crashlytics}@aar"
            const val appCompat =
                "androidx.appcompat:appcompat:${Versions.Libs.Android.appCompat}"
            const val material =
                "com.google.android.material:material:${Versions.Libs.Android.material}"
            const val lifecycle =
                "androidx.lifecycle:lifecycle-extensions:${Versions.Libs.Android.lifecycle}"
            const val recyclerView =
                "androidx.recyclerview:recyclerview:${Versions.Libs.Android.recyclerView}"
            const val navigationUi =
                "android.arch.navigation:navigation-ui-ktx:${Versions.Libs.Android.navigation}"
            const val navigationFragment =
                "android.arch.navigation:navigation-fragment-ktx:${Versions.Libs.Android.navigation}"
        }

        object MultiPlatform {
            val kotlinStdLib = MultiPlatformLibrary(
                android = Android.kotlinStdLib,
                common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
            )
            val ktorClient = MultiPlatformLibrary(
                android = "io.ktor:ktor-client-android:${Versions.Libs.MultiPlatform.ktorClient}",
                common = "io.ktor:ktor-client-core:${Versions.Libs.MultiPlatform.ktorClient}",
                ios = "io.ktor:ktor-client-ios:${Versions.Libs.MultiPlatform.ktorClient}"
            )
            val ktorClientLogging = MultiPlatformLibrary(
                android = "io.ktor:ktor-client-logging-jvm:${Versions.Libs.MultiPlatform.ktorClientLogging}",
                common = "io.ktor:ktor-client-logging:${Versions.Libs.MultiPlatform.ktorClientLogging}",
                ios = "io.ktor:ktor-client-logging-native:${Versions.Libs.MultiPlatform.ktorClientLogging}"
            )
            val coroutines = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.MultiPlatform.coroutines}",
                common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.Libs.MultiPlatform.coroutines}",
                ios = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.Libs.MultiPlatform.coroutines}"
            )
            val serialization = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.Libs.MultiPlatform.serialization}",
                common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.Libs.MultiPlatform.serialization}",
                ios = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.Libs.MultiPlatform.serialization}"
            )
            val klock = MultiPlatformLibrary(
                common = "com.soywiz.korlibs.klock:klock:${Versions.Libs.MultiPlatform.klock}"
            )

        }
    }

    object Kapt {
        const val androidLifecycle =
            "androidx.lifecycle:lifecycle-compiler:${Versions.Libs.Android.androidArchitecture}"
    }

    val plugins: Map<String, String> = mapOf(
        "com.android.application" to Plugins.android,
        "com.android.library" to Plugins.android,
        "org.jetbrains.kotlin.multiplatform" to Plugins.kotlin,
        "kotlin-kapt" to Plugins.kotlin,
        "kotlin-android" to Plugins.kotlin,
        "kotlinx-serialization" to Plugins.serialization,
        "kotlin-android-extensions" to Plugins.androidExtensions,
        "io.fabric" to Plugins.fabric
    )
}