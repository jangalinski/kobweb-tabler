package com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib

import com.github.jangalinski.kobweb.tabler.gradle.buildlogic.BuildLogic
import com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib.task.GenerateLibCodeTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A Gradle plugin for the Tabweb library.
 */
class TabwebLibPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project.tasks) {
      register(GenerateLibCodeTask.NAME, GenerateLibCodeTask::class.java) {
        group = GenerateLibCodeTask.GROUP
        description = GenerateLibCodeTask.DESCRIPTION
        outputDirectory.convention(project.layout.buildDirectory.dir(BuildLogic.DEFAULT_OUTPUT_DIRECTORY))
      }
    }
  }
}
