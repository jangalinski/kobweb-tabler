package com.github.jangalinski.kobweb.tabler.buildlogic

import assertk.assertThat
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isEqualTo
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test

class TablerIconsPluginTest {

  @Test
  fun `parses and prints icon names`() {
    val css = """
      .ti ti { }
      .ti-a-b:before { content: "\\ec36" }
      .ti-brand-denodo:before { content: "\\f123" }
      .ti-brand-denodo:before { content: "\\f123" }
    """.trimIndent()

    val names = parseTablerIconNames(css)
    names.forEach(::println)

    assertThat(names).containsExactlyInAnyOrder("ti-a-b", "ti-brand-denodo")
  }

  @Test
  fun `registers download and print tasks`() {
    val project: Project = ProjectBuilder.builder().build()

    project.pluginManager.apply(TablerIconsPlugin::class.java)

    assertThat(
      project.tasks.names.filter {
        it in setOf("downloadTablerIconsCss", "printTablerIconNames", "generateTablerIcon")
      }
    ).containsExactlyInAnyOrder(
      "downloadTablerIconsCss",
      "printTablerIconNames",
      "generateTablerIcon"
    )
  }

  @Test
  fun `generates sorted enum values using exact css names`() {
    val source = generateTablerIconSource(listOf("ti-brand-github", "ti-footsteps", "ti-2fa"), "3.46.0")

    assertThat(source).isEqualTo(
      """
        package com.github.jangalinski.kobweb.tabler.elements

        import com.varabyte.kobweb.compose.ui.Modifier
        import com.varabyte.kobweb.compose.ui.modifiers.classNames

        /** Generated from Tabler Icons CSS 3.46.0. Do not edit manually. */
        enum class TablerIcon(private val modifier: Modifier) : Modifier by modifier {
          TI_2FA("ti-2fa"),
          TI_BRAND_GITHUB("ti-brand-github"),
          TI_FOOTSTEPS("ti-footsteps"),
          ;

          constructor(style: String) : this(Modifier.classNames("ti", style))
        }
      """.trimIndent() + "\n"
    )
  }
}
