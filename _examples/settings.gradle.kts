pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
  }

  // Make the build-logic composite available as a plugin source for `plugins {}` resolution.
  includeBuild("../gradle/build-logic")
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    google()
  }

  // `_examples` is its own Gradle build, so it needs to import the shared version catalog explicitly.
  versionCatalogs {
    create("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }
}

rootProject.name = "kobweb-tabler-examples"

include(":tagessieg")

includeBuild("../") {
  dependencySubstitution {
    substitute(module("com.github.jangalinski.kobweb-tabler:kobweb-tabler")).using(project(":"))
  }
}
