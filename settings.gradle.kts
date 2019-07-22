import java.net.URI

pluginManagement {
    repositories {
        jcenter()
        google()

        maven { url = URI("https://dl.bintray.com/kotlin/kotlin") }
        maven { url = URI("https://kotlin.bintray.com/kotlinx") }
        maven { url = URI("https://jetbrains.bintray.com/kotlin-native-dependencies") }
        maven { url = URI("https://maven.fabric.io/public") }
    }
    resolutionStrategy.eachPlugin {
        val module = Deps.plugins[requested.id.id] ?: return@eachPlugin

        useModule(module)
    }
}

enableFeaturePreview("GRADLE_METADATA")

include(":android-app")

listOf(
    Modules.MultiPlatform.library
).forEach { include(it) }
