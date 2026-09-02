plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

gradlePlugin {
  plugins {
    create("tagessiegPreview") {
      id = "com.github.jangalinski.kobweb.tabler.buildlogic.tagessieg-preview"
      implementationClass = "com.github.jangalinski.kobweb.tabler.buildlogic.ExamplePreviewPlugin"
    }
  }
}
