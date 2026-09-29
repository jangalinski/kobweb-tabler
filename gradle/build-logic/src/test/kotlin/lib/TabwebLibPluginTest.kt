package com.github.jangalinski.tabweb.gradle.buildlogic.lib

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.exists
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.github.jangalinski.tabweb.gradle.buildlogic.lib.model.ColorsModel
import com.github.jangalinski.tabweb.gradle.buildlogic.lib.task.GenerateLibCodeTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import kotlin.test.Test

class TabwebLibPluginTest {

  @Test
  fun `registers generateLibCode task with group and description`() {
    val project: Project = ProjectBuilder.builder().build()

    project.pluginManager.apply(TabwebLibPlugin::class.java)

    val task = project.tasks.findByName(GenerateLibCodeTask.NAME)
    assertThat(task).isNotNull()
    assertThat(task?.group).isEqualTo(GenerateLibCodeTask.GROUP)
    assertThat(task?.description).isEqualTo(GenerateLibCodeTask.DESCRIPTION)
    assertThat(project.tasks.names).contains(GenerateLibCodeTask.NAME)
  }

  @Test
  fun `read model`() {
    println(ColorsModel.load())
  }

  @Test
  fun `generateLibCode task writes BackgroundColor to target directory`() {
    val project: Project = ProjectBuilder.builder().build()
    project.pluginManager.apply(TabwebLibPlugin::class.java)

    val task = project.tasks.getByName(GenerateLibCodeTask.NAME) as GenerateLibCodeTask
    val testOutputDir = File(project.layout.buildDirectory.asFile.get(), "test-generated")
    task.outputDirectory.set(testOutputDir)

    task()

    val generatedFile = File(testOutputDir, "com/github/jangalinski/tabweb/_foundation/modifier/BackgroundColor.kt")
    println("Generated file path: ${generatedFile.absolutePath}")
    if (generatedFile.exists()) {
      println("Generated file content:\n${generatedFile.readText()}")
    }

    assertThat(generatedFile).exists()
  }
}
