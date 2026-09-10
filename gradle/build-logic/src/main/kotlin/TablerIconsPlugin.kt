package com.github.jangalinski.kobweb.tabler.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import java.net.URI
import java.nio.file.Files
import java.util.Locale

private const val DEFAULT_CSS_URL =
  "https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@3.46.0/dist/tabler-icons.min.css"

abstract class TablerIconsExtension {
  /** The Tabler Icons CSS file to download. */
  abstract val cssUrl: Property<String>

  /** The Tabler Icons version represented by the CSS file. */
  abstract val version: Property<String>
}

abstract class DownloadTablerIconsCssTask : DefaultTask() {
  @get:Input
  abstract val cssUrl: Property<String>

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @TaskAction
  fun download() {
    val destination = outputFile.get().asFile.toPath()
    Files.createDirectories(destination.parent)
    URI(cssUrl.get()).toURL().openStream().use { input ->
      Files.newOutputStream(destination).use { output ->
        input.copyTo(output)
      }
    }
    logger.lifecycle("Downloaded Tabler Icons CSS to $destination")
  }
}

abstract class PrintTablerIconNamesTask : DefaultTask() {
  @get:InputFile
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val cssFile: RegularFileProperty

  @TaskAction
  fun printNames() {
    val names = parseTablerIconNames(cssFile.get().asFile.readText())
    names.forEach(logger::lifecycle)
    logger.lifecycle("Found ${names.size} Tabler icon names")
  }
}

abstract class GenerateTablerIconTask : DefaultTask() {
  @get:InputFile
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val cssFile: RegularFileProperty

  @get:OutputFile
  abstract val outputFile: RegularFileProperty

  @get:Input
  abstract val version: Property<String>

  @TaskAction
  fun generate() {
    val names = parseTablerIconNames(cssFile.get().asFile.readText())
    val source = generateTablerIconSource(names, version.get())
    val destination = outputFile.get().asFile.toPath()
    Files.createDirectories(destination.parent)
    destination.toFile().writeText(source)
    logger.lifecycle("Generated TablerIcon with ${names.size} values at $destination")
  }
}

class TablerIconsPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val extension = project.extensions.create("tablerIcons", TablerIconsExtension::class.java)
    extension.cssUrl.convention(DEFAULT_CSS_URL)
    extension.version.convention("3.46.0")

    val downloadTask = project.tasks.register(
      "downloadTablerIconsCss",
      DownloadTablerIconsCssTask::class.java
    ) {
      group = "tabler"
      description = "Downloads the configured Tabler Icons CSS file."
      cssUrl.set(extension.cssUrl)
      outputFile.convention(project.layout.buildDirectory.file("tabler-icons/tabler-icons.css"))
    }

    project.tasks.register("printTablerIconNames", PrintTablerIconNamesTask::class.java) {
      group = "tabler"
      description = "Prints all Tabler icon CSS class names."
      cssFile.set(downloadTask.flatMap { it.outputFile })
      dependsOn(downloadTask)
    }

    val generateTask = project.tasks.register("generateTablerIcon", GenerateTablerIconTask::class.java) {
      group = "tabler"
      description = "Generates the TablerIcon source from the downloaded CSS file."
      cssFile.set(downloadTask.flatMap { it.outputFile })
      outputFile.convention(
        project.layout.projectDirectory.file("src/jsMain/kotlin/elements/TablerIcon.kt")
      )
      version.set(extension.version)
      dependsOn(downloadTask)
    }
  }
}

internal fun parseTablerIconNames(css: String): List<String> =
  Regex("""\.(ti-[a-z0-9-]+):before\s*\{""")
    .findAll(css)
    .map { it.groupValues[1] }
    .distinct()
    .sorted()
    .toList()

internal fun generateTablerIconSource(names: List<String>, version: String): String = buildString {
  appendLine("package com.github.jangalinski.kobweb.tabler.elements")
  appendLine()
  appendLine("import com.varabyte.kobweb.compose.ui.Modifier")
  appendLine("import com.varabyte.kobweb.compose.ui.modifiers.classNames")
  appendLine()
  appendLine("/** Generated from Tabler Icons CSS $version. Do not edit manually. */")
  appendLine("enum class TablerIcon(private val modifier: Modifier) : Modifier by modifier {")
  names.sorted().forEach { cssName ->
    val enumName = cssName.uppercase(Locale.ROOT)
      .replace('-', '_')
    appendLine("  $enumName(\"$cssName\"),")
  }
  appendLine("  ;")
  appendLine()
  appendLine("  constructor(style: String) : this(Modifier.classNames(\"ti\", style))")
  appendLine("}")
}
