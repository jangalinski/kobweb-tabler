plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

dependencies {
  testImplementation(kotlin("test"))
  testImplementation(libs.test.assertk)
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
  }
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    showStandardStreams = true
  }
}
