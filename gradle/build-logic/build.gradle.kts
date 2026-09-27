plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
  id("org.jetbrains.kotlin.plugin.serialization") version embeddedKotlinVersion
}

dependencies {
  implementation(platform(libs.bom.kotlin.code.generation))

  implementation(libs.kotlinx.serialization.json)
  implementation(libs.kotlin.code.generation)

  testImplementation(kotlin("test-junit5"))
  testImplementation(libs.test.assertk)
}

configurations.all {
  // TODO - only for SNAPSHOTS
  resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

gradlePlugin {
  plugins {
    create("tagessiegPreview") {
      id = "com.github.jangalinski.kobweb.tabler.buildlogic.tagessieg-preview"
      implementationClass = "com.github.jangalinski.kobweb.tabler.gradle.buildlogic.ExamplePreviewPlugin"
    }
    create("sitePreview") {
      id = "com.github.jangalinski.kobweb.tabler.buildlogic.site-preview"
      implementationClass = "com.github.jangalinski.kobweb.tabler.gradle.buildlogic.SitePreviewPlugin"
    }
    create("tablerIcons") {
      id = "com.github.jangalinski.kobweb.tabler.buildlogic.tabler-icons"
      implementationClass = "com.github.jangalinski.kobweb.tabler.gradle.buildlogic.TablerIconsPlugin"
    }
    create("tablerCssDocumentation") {
      id = "com.github.jangalinski.kobweb.tabler.buildlogic.tabler-css-documentation"
      implementationClass = "com.github.jangalinski.kobweb.tabler.gradle.buildlogic.TablerCssDocumentationPlugin"
    }
    create("kotlinCodeGeneration") {
      id = "com.github.jangalinski.kobweb.tabler.buildlogic.kotlin-code-generation"
      implementationClass = "com.github.jangalinski.kobweb.tabler.gradle.buildlogic.generation.KotlinCodeGenerationPlugin"
    }

    create("tabwebLib") {
      id = "buildlogic.tabweb-lib"
      implementationClass = "com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib.TabwebLibPlugin"
    }
  }
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    showStandardStreams = true
  }
}
