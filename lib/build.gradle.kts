import com.varabyte.kobweb.gradle.core.util.importCss
import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary
import kotlinx.html.script
import kotlinx.html.style
import org.gradle.api.publish.maven.MavenPublication

val TABLER_LAYER = "kobweb-tabler"

val TABLER_VERSION = "1.4.0"
val TABLER_ICONS_VERSION = "3.46.0"

val TABLER_CSS = "https://cdn.jsdelivr.net/npm/@tabler/core@$TABLER_VERSION/dist/css/tabler.min.css"
val TABLER_JS = "https://cdn.jsdelivr.net/npm/@tabler/core@$TABLER_VERSION/dist/js/tabler.min.js"
val TABLER_ICONS_CSS = "https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@$TABLER_ICONS_VERSION/dist/tabler-icons.min.css"

val APEXCHARTS_JS = "https://cdn.jsdelivr.net/npm/apexcharts"

plugins {
  `maven-publish`
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kobweb.library)
}

base {
  archivesName.set("kobweb-tabler")
}

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

    jsTest.dependencies {
      implementation(kotlin("test-js"))
      implementation(libs.compose.html.test.utils)
      implementation(libs.assertk)
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
          importCss(
            url = TABLER_ICONS_CSS,
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

publishing {
  publications.withType<MavenPublication>().configureEach {
    artifactId = when (name) {
      "kotlinMultiplatform" -> "kobweb-tabler"
      "js" -> "kobweb-tabler-js"
      else -> artifactId
    }
  }
}
