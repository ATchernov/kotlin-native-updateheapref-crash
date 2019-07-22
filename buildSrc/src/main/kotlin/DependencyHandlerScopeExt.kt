import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

/**
 * Created by Aleksey Mikhailov <Aleksey.Mikhailov@icerockdev.com> on 2019-07-05.
 */

fun DependencyHandlerScope.mppModule(name: String) {
    "androidMainImplementation"(dependencies.project(path = name))
    "commonMainApi"(dependencies.project(path = name))
    "iosX64MainImplementation"(dependencies.project(path = name))
    "iosArm64MainImplementation"(dependencies.project(path = name))
}

fun DependencyHandlerScope.projectModule(name: String) {
    "implementation"(dependencies.project(path = name))
}

data class MultiPlatformLibrary(
    val android: String? = null,
    val common: String? = null,
    val ios: String? = null
)

fun DependencyHandlerScope.mppLibrary(library: MultiPlatformLibrary) {
    library.android?.let { "androidMainImplementation"(it) }
    library.common?.let { "commonMainApi"(it) }
    library.ios?.let {
        "iosX64MainImplementation"(it)
        "iosArm64MainImplementation"(it)
    }
}

fun DependencyHandlerScope.androidLibrary(name: String) {
    "androidMainImplementation"(name)
}

fun DependencyHandlerScope.kaptLibrary(name: String) {
    "kapt"(name)
}
