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
      implementationClass = "com.github.jangalinski.kobweb.tabler.buildlogic.ExamplePreviewPlugin"
    }
    create("tablerIcons") {
      id = "com.github.jangalinski.kobweb.tabler.buildlogic.tabler-icons"
      implementationClass = "com.github.jangalinski.kobweb.tabler.buildlogic.TablerIconsPlugin"
    }
  }
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    showStandardStreams = true
  }
}
