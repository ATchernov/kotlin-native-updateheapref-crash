plugins {
    id("com.android.application")
    id("io.fabric")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    signingConfigs {
        create("release") {
            keyAlias = System.getenv("RELEASE_KEY_ALIAS")
            keyPassword = System.getenv("RELEASE_KEY_PASSWORD")
            storeFile = file("../signing/keystore.jks")
            storePassword = System.getenv("RELEASE_STORE_PASSWORD")
        }
    }

    compileSdkVersion(Versions.Android.compileSdk)

    dataBinding {
        isEnabled = true
    }

    dexOptions {
        javaMaxHeapSize = "2g"
    }

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)

        applicationId = "com.icerockdev.test"

        versionCode = Integer.parseInt(project.property("VERSION_CODE") as String)
        versionName = project.property("VERSION_NAME") as String

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            ext.set("enableCrashlytics", false)
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    flavorDimensions("server")

    productFlavors {
        create("dev") {
            setDimension("server")

            applicationIdSuffix = ".dev"

            val endpoint = "https://api.github.com/"
            buildConfigField("String", "SERVER_ENDPOINT", "\"$endpoint\"")
        }

        create("prod") {
            setDimension("server")

            val endpoint = "https://api.github.com/"
            buildConfigField("String", "SERVER_ENDPOINT", "\"$endpoint\"")
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    implementation(Deps.Libs.Android.kotlinStdLib)

    implementation(Deps.Libs.Android.crashlytics) { isTransitive = true }
    implementation(Deps.Libs.Android.appCompat)
    implementation(Deps.Libs.Android.material)

    projectModule(Modules.MultiPlatform.library)
}

// генератор схемы зависимостей
apply(from = "https://raw.githubusercontent.com/JakeWharton/SdkSearch/master/gradle/projectDependencyGraph.gradle")
