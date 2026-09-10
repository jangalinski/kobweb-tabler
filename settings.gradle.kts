dependencyResolutionManagement {
  repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    google()
  }
}

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
  }

  includeBuild("gradle/build-logic")
}

rootProject.name = "kobweb-tabler"

include(":lib")
include(":site")
