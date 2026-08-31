plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

gradlePlugin {
  plugins {
    create("tagessiegPreview") {
      id = "io.github.jangalinski.kotlin.kobweb.tabler.buildlogic.tagessieg-preview"
      implementationClass = "io.github.jangalinski.kotlin.kobweb.tabler.buildlogic.ExamplePreviewPlugin"
    }
  }
}
