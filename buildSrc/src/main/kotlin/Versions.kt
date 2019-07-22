object Versions {
    object Android {
        const val compileSdk = 28
        const val targetSdk = 28
        const val minSdk = 21
    }

    const val kotlin = "1.3.41"

    object Plugins {
        const val android = "3.4.1"
        const val kotlin = Versions.kotlin
        const val serialization = Versions.kotlin
        const val androidExtensions = Versions.kotlin
        const val fabric = "1.29.0"
    }

    object Libs {
        object Android {
            const val navigation = "1.0.0"
            const val material = "1.0.0"
            const val appCompat = "1.0.2"
            const val crashlytics = "2.9.6"
            const val androidArchitecture = "2.0.0"
            const val socketIoClient = "1.0.0"
            const val annotations = "1.0.0"
            const val constraintLayout = "1.1.3"
            const val recyclerView = "1.0.0"
            const val cardView = "1.0.0"
            const val androidxCore = "1.0.1"
            const val lifecycle = "2.0.0"
            const val leakCanary = "1.5.4"
            const val playServicesLocation = "16.0.0"
            const val playServicesMaps = "16.1.0"
            const val googleMapsServices = "0.2.11"
            const val glide = "4.8.0"
        }
        object MultiPlatform {
            const val coroutines = "1.2.2"
            const val serialization = "0.11.1"
            const val ktorClient = "1.2.2"
            const val ktorClientLogging = ktorClient
            const val klock = "1.5.0"
        }
        const val jUnit = "4.12"
    }
}