package com.github.jangalinski.tabweb.gradle.buildlogic

data object BuildLogic {
  // marker for root project

  const val DEFAULT_OUTPUT_DIRECTORY = "generated/sources/tabweb/kotlin"

  fun loadResource(resource: String) = {}
    .javaClass.getResource(resource.takeIf { it.startsWith("/") } ?: "/$resource")
    ?.readText()
    ?: throw IllegalArgumentException("Resource not found: $resource")
}
