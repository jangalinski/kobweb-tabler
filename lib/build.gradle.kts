import com.varabyte.kobweb.gradle.core.util.importCss
import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary
import kotlinx.html.script
import kotlinx.html.style
import org.gradle.api.publish.maven.MavenPublication

val KOBWEB_TABLER = "kobweb-tabler"

plugins {
  `maven-publish`
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.dokka)
  alias(libs.plugins.kobweb.library)
}

base {
  archivesName.set(KOBWEB_TABLER)
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
            url = "https://cdn.jsdelivr.net/npm/@tabler/core@${libs.versions.cdn.tabler.core.get()}/dist/css/tabler.min.css",
            layerName = KOBWEB_TABLER
          )
          importCss(
            url = "https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@${libs.versions.cdn.tabler.icons.get()}/dist/tabler-icons.min.css",
            layerName = KOBWEB_TABLER
          )
        }
        script {
          src = "https://cdn.jsdelivr.net/npm/@tabler/core@${libs.versions.cdn.tabler.core.get()}/dist/js/tabler.min.js"
        }
        script {
          src = "https://cdn.jsdelivr.net/npm/apexcharts"
        }
      }
    }
  }
}

dokka {
  dokkaPublications.html {
    moduleName.set(KOBWEB_TABLER)
    moduleVersion.set(project.version.toString())
  }
  dokkaSourceSets.configureEach {
    includes.from(
      fileTree(rootProject.file("docs/dokka")) {
        include("**/*.md")
      }
    )
  }
}

publishing {
  publications.withType<MavenPublication>().configureEach {
    artifactId = when (name) {
      "kotlinMultiplatform" -> KOBWEB_TABLER
      "js" -> "$KOBWEB_TABLER-js"
      else -> artifactId
    }
  }
}
