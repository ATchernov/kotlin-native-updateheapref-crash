import java.net.URI

allprojects {
    repositories {
        google()
        jcenter()

        maven { url = URI("https://kotlin.bintray.com/kotlin") }
        maven { url = URI("https://kotlin.bintray.com/ktor") }
        maven { url = URI("https://kotlin.bintray.com/kotlinx") }
        maven { url = URI("https://maven.fabric.io/public") }
        maven { url = URI("https://dl.bintray.com/korlibs/korlibs") }
        maven { url = URI("https://dl.bintray.com/lukaville/maven") }
    }

    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")
}

tasks.register("clean", Delete::class).configure {
    delete(rootProject.buildDir)
}
