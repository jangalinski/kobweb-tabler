package net.janhoo.kotlin.kobweb.tabler.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskProvider
import org.gradle.work.DisableCachingByDefault as WorkDisableCachingByDefault
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption.REPLACE_EXISTING


@WorkDisableCachingByDefault(because = "Mirrors generated export output into a serving layout.")
abstract class MirrorStaticExportTask : DefaultTask() {
  @get:Input
  abstract val basePath: Property<String>

  @get:InputDirectory
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val siteRoot: DirectoryProperty

  @TaskAction
  fun mirror() {
    val exportSitePath = siteRoot.get().asFile.toPath()
    val basePathValue = basePath.get()
    val mirrorRoot = exportSitePath.resolve(basePathValue)

    logger.lifecycle("Mirroring exported site to ${mirrorRoot.toAbsolutePath()}")
    deleteRecursively(mirrorRoot)
    Files.createDirectories(mirrorRoot)

    Files.walk(exportSitePath).use { stream ->
      stream.filter { Files.isRegularFile(it) }.forEach { source ->
        val relative = exportSitePath.relativize(source).toString().replace('\\', '/')
        if (relative == basePathValue || relative.startsWith("$basePathValue/")) return@forEach

        copyMirroredFile(source, mirrorRoot.resolve(relative))

        when {
          relative.startsWith("pages/") -> {
            val alias = stripKobwebExportPrefix(relative)
            copyMirroredFile(source, mirrorRoot.resolve(mirrorAliasPath(alias)))
          }

          relative.startsWith("resources/") -> {
            val alias = stripKobwebExportPrefix(relative)
            copyMirroredFile(source, mirrorRoot.resolve(alias))
          }

          relative.startsWith("system/") -> {
            val alias = stripKobwebExportPrefix(relative)
            if (alias != "index.html") {
              copyMirroredFile(source, mirrorRoot.resolve(alias))
            }
          }
        }
      }
    }
  }
}

class TagessiegPreviewPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    registerMirrorTask(project)
  }

  private fun registerMirrorTask(project: Project): TaskProvider<MirrorStaticExportTask> =
    project.tasks.register("mirrorExportForPlainStaticServer", MirrorStaticExportTask::class.java) {
      group = "kobweb"
      description = "Mirrors Kobweb export output into a base-path directory tree for plain static servers."
      basePath.convention("tagessieg")
      siteRoot.convention(project.layout.projectDirectory.dir(".kobweb/site"))
    }
}

private fun deleteRecursively(path: Path) {
  if (!Files.exists(path)) return

  Files.walk(path).use { stream ->
    stream.sorted(compareByDescending { it.nameCount }).forEach { Files.deleteIfExists(it) }
  }
}

private fun mirrorAliasPath(relativeHtmlPath: String): Path =
  when {
    relativeHtmlPath == "index.html" -> Path.of("index.html")
    relativeHtmlPath.endsWith("/index.html") -> Path.of(relativeHtmlPath)
    relativeHtmlPath.endsWith(".html") -> Path.of(relativeHtmlPath.removeSuffix(".html")).resolve("index.html")
    else -> Path.of(relativeHtmlPath)
  }

private fun stripKobwebExportPrefix(relativePath: String): String =
  when {
    relativePath.startsWith("pages/") -> relativePath.removePrefix("pages/")
    relativePath.startsWith("resources/") -> relativePath.removePrefix("resources/")
    relativePath.startsWith("system/") -> relativePath.removePrefix("system/")
    else -> relativePath
  }

private fun copyMirroredFile(source: Path, target: Path) {
  Files.createDirectories(target.parent)
  Files.copy(source, target, REPLACE_EXISTING)
}
