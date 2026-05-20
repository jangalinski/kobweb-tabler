plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

gradlePlugin {
  plugins {
    create("tagessiegPreview") {
      id = "net.janhoo.kotlin.kobweb.tabler.buildlogic.tagessieg-preview"
      implementationClass = "net.janhoo.kotlin.kobweb.tabler.buildlogic.TagessiegPreviewPlugin"
    }
  }
}
