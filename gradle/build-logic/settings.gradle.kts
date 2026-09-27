dependencyResolutionManagement {
  repositories {
    mavenLocal {
      content {
        includeGroupByRegex("io\\.toolisticon.*")
      }
    }
    gradlePluginPortal()
    mavenCentral()
  }

  versionCatalogs {
    create("libs") {
      from(files("../libs.versions.toml"))
    }
  }
}

rootProject.name = "build-logic"
