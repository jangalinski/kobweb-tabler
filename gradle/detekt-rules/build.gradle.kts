plugins {
  alias(libs.plugins.kotlin.jvm)
}

group = "com.github.jangalinski.kobweb.tabler.gradle"

repositories {
  mavenCentral()
}

dependencies {
  compileOnly(libs.detekt.api)
  testImplementation(kotlin("test"))
  testImplementation(libs.detekt.test)
}

kotlin {
  // Detekt executes custom rules in the Gradle JVM, which is Java 17 for this build.
  jvmToolchain(17)
}
