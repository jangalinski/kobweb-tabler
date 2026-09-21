package com.github.jangalinski.kobweb.tabler.gradle.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
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
  @get:InputDirectory
  @get:PathSensitive(PathSensitivity.RELATIVE)
  abstract val siteRoot: DirectoryProperty

  @get:org.gradle.api.tasks.OutputDirectory
  abstract val mirrorRoot: DirectoryProperty

  @TaskAction
  fun mirror() {
    val exportSitePath = siteRoot.get().asFile.toPath()
    val mirrorSitePath = mirrorRoot.get().asFile.toPath()
    val mirrorRelative = if (mirrorSitePath.startsWith(exportSitePath)) {
      exportSitePath.relativize(mirrorSitePath).toString().replace('\\', '/')
    } else {
      null
    }

    logger.lifecycle("Mirroring exported site to ${mirrorSitePath.toAbsolutePath()}")
    deleteRecursively(mirrorSitePath)
    Files.createDirectories(mirrorSitePath)

    Files.walk(exportSitePath).use { stream ->
      stream.filter { Files.isRegularFile(it) }.forEach { source ->
        val relative = exportSitePath.relativize(source).toString().replace('\\', '/')
        if (mirrorRelative != null && (relative == mirrorRelative || relative.startsWith("$mirrorRelative/"))) {
          return@forEach
        }

        when {
          relative.startsWith("pages/") -> {
            // pages/*.html are empty Kobweb routing shells; serve system/index.html (real app shell) for every route
            val alias = stripKobwebExportPrefix(relative)
            val systemIndex = exportSitePath.resolve("system/index.html")
            val appShell = if (Files.exists(systemIndex)) systemIndex else source
            copyMirroredFile(appShell, mirrorSitePath.resolve(mirrorAliasPath(alias)))
          }

          relative.endsWith(".html") && relative != "index.html" -> {
            copyMirroredFile(source, mirrorSitePath.resolve(mirrorAliasPath(relative)))
          }

          relative.startsWith("resources/") -> {
            val alias = stripKobwebExportPrefix(relative)
            copyMirroredFile(source, mirrorSitePath.resolve(alias))
          }

          relative.startsWith("system/") -> {
            val alias = stripKobwebExportPrefix(relative)
            copyMirroredFile(source, mirrorSitePath.resolve(alias))
          }

          else -> copyMirroredFile(source, mirrorSitePath.resolve(relative))
        }
      }
    }

    // system/index.html is the real app shell with the JS bootstrap; always write it last
    // so it overrides the empty Kobweb routing shell placed by pages/index.html
    val systemIndex = exportSitePath.resolve("system/index.html")
    if (Files.exists(systemIndex)) {
      copyMirroredFile(systemIndex, mirrorSitePath.resolve("index.html"))
    }
  }
}

class ExamplePreviewPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val mirrorTask = registerMirrorTask(project)
    wireTaskDependencies(project, mirrorTask)
  }

  private fun registerMirrorTask(project: Project): TaskProvider<MirrorStaticExportTask> =
    project.tasks.register("mirrorExportForPlainStaticServer", MirrorStaticExportTask::class.java) {
      group = "kobweb"
      description = "Mirrors Kobweb export output into a base-path directory tree for plain static servers."
      siteRoot.convention(project.layout.projectDirectory.dir(".kobweb/site"))
      mirrorRoot.convention(project.layout.projectDirectory.dir(".kobweb/site/${project.name}"))
    }

  private fun wireTaskDependencies(project: Project, mirrorTask: TaskProvider<MirrorStaticExportTask>) {
    project.tasks.matching { it.name == "kobwebExport" }.configureEach {
      finalizedBy(mirrorTask)
    }
  }
}

class SitePreviewPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val mirrorTask = project.tasks.register("mirrorExportForPlainStaticServer", MirrorStaticExportTask::class.java) {
      group = "kobweb"
      description = "Mirrors the site export into directory routes for a plain static server."
      siteRoot.convention(project.layout.projectDirectory.dir(".kobweb/site"))
      mirrorRoot.convention(project.rootProject.layout.buildDirectory.dir("site-preview"))
    }

    project.tasks.matching { it.name == "kobwebExport" }.configureEach {
      finalizedBy(mirrorTask)
    }
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
