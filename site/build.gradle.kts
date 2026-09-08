import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.kobweb.application)
}

kobweb {
  pagesPackage = "com.github.jangalinski.kobweb.tabler.site.pages"

  app {
    index {
      description.set("Kobweb Tabler documentation and examples")
    }
  }
}

kotlin {
  configAsKobwebApplication()

  sourceSets {
    commonMain.dependencies {
      implementation(libs.kobweb.core)
    }

    jsMain.dependencies {
      implementation(project(":lib"))
      implementation(libs.compose.runtime)
      implementation(libs.compose.html.core)
      implementation(libs.kobweb.compose.js)
      implementation(libs.kobweb.silk)
    }
  }
}
