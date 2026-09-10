import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.ktx.compose)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.kobweb.application)
  id("com.github.jangalinski.kobweb.tabler.buildlogic.tagessieg-preview")
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
