import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
  alias(libs.plugins.kotlin.multiplatform) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.jetbrains.compose) apply false
  alias(libs.plugins.kobweb.application) apply false
  alias(libs.plugins.kobweb.library) apply false
}

allprojects {
  group = providers.environmentVariable("GROUP").orElse("com.github.jangalinski").get()
  version = providers.environmentVariable("VERSION").orElse("0.0.1-SNAPSHOT").get()
}

plugins.withType<YarnPlugin> {
  rootProject.extensions.getByType<YarnRootExtension>().lockFileDirectory =
    rootProject.file("gradle/kotlin-js-store")
}

subprojects {
  plugins.withType<YarnPlugin> {
    rootProject.extensions.getByType<YarnRootExtension>().lockFileDirectory =
      rootProject.file("gradle/kotlin-js-store")
  }
}
