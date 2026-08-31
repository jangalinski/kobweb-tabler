import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.ktx.compose)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.kobweb.application)
  id("io.github.jangalinski.kotlin.kobweb.tabler.buildlogic.tagessieg-preview")
}

kobweb {
  pagesPackage = "io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg.pages"
}

rootProject.plugins.withType<YarnPlugin> {
  rootProject.extensions.getByType<YarnRootExtension>().lockFileDirectory = rootProject.file("gradle/kotlin-js-store")
}

kotlin {
  configAsKobwebApplication()

  sourceSets {
    commonMain.dependencies {
      implementation(libs.kobweb.core)
    }

    jsMain.dependencies {
      implementation(libs.kobweb.tabler)
      implementation(libs.kobweb.silk)
      implementation(libs.compose.runtime)
      implementation(libs.compose.html.core)
      implementation(libs.kobweb.compose.js)
    }
  }
}
