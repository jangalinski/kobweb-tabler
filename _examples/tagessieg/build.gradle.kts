import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import org.gradle.api.tasks.Copy
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.ktx.compose)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.kobweb.application)
}

kobweb {
  pagesPackage = "net.janhoo.kotlin.kobweb.tabler.example.tagessieg.pages"
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
      implementation("org.jetbrains.compose.runtime:runtime:1.10.2")
      implementation("org.jetbrains.compose.html:html-core:1.10.0")
      implementation(libs.kobweb.compose.js)
    }
  }
}

val copyPublicResourcesToKobwebDevRoot by tasks.registering(Copy::class) {
  from("src/jsMain/resources/public")
  into(layout.buildDirectory.dir("generated/kobweb/app/src/jsMain/resources"))
}

tasks {
  named("kobwebStart") {
    dependsOn(copyPublicResourcesToKobwebDevRoot)
  }

  named("kobwebCacheAppFrontendData") {
    dependsOn(copyPublicResourcesToKobwebDevRoot)
  }

  named("kobwebGenSiteEntry") {
    dependsOn(copyPublicResourcesToKobwebDevRoot)
  }

  named("kobwebCopySupplementalResources") {
    dependsOn(copyPublicResourcesToKobwebDevRoot)
  }
}
