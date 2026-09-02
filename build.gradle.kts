import com.varabyte.kobweb.gradle.core.util.importCss
import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary
import kotlinx.html.script
import kotlinx.html.style
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

val TABLER_VERSION = "1.4.0"
val TABLER_LAYER = "kobweb-tabler"
val TABLER_CSS = "https://cdn.jsdelivr.net/npm/@tabler/core@$TABLER_VERSION/dist/css/tabler.min.css"
val TABLER_JS = "https://cdn.jsdelivr.net/npm/@tabler/core@$TABLER_VERSION/dist/js/tabler.min.js"
val APEXCHARTS_JS = "https://cdn.jsdelivr.net/npm/apexcharts"

plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.library)
}

group = providers.environmentVariable("GROUP").orElse("com.github.jangalinski").get()
version = providers.environmentVariable("VERSION").orElse("0.0.1-SNAPSHOT").get()

kotlin {
    configAsKobwebLibrary(includeServer = false)

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.compose.js)
            implementation(libs.kobweb.silk)
        }
    }
}

kobweb {
    library {
        index {
            head.add {
                style {
                    importCss(
                        url = TABLER_CSS,
                        layerName = TABLER_LAYER
                    )
                }
                script {
                    src = TABLER_JS
                }
                script {
                    src = APEXCHARTS_JS
                }
            }
        }
    }
}


rootProject.plugins.withType<YarnPlugin> {
    rootProject.extensions.getByType<YarnRootExtension>().lockFileDirectory = rootProject.file("gradle/kotlin-js-store")
}
