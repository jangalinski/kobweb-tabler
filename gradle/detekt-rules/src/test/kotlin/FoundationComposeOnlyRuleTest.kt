package com.github.jangalinski.kobweb.tabler.gradle.detekt

import dev.detekt.api.Config
import dev.detekt.test.lint
import kotlin.test.Test
import kotlin.test.assertEquals

class FoundationComposeOnlyRuleTest {

  @Test
  fun `reports a Compose DOM import outside the foundation DSL`() {
    val findings = FoundationComposeOnlyRule(Config.empty).lint(
      """
      package com.github.jangalinski.kobweb.tabler.card

      import org.jetbrains.compose.web.dom.Div
      """.trimIndent(),
    )

    assertEquals(1, findings.size)
  }

  @Test
  fun `reports a W3C DOM import outside the foundation DSL`() {
    val findings = FoundationComposeOnlyRule(Config.empty).lint(
      """
      package com.github.jangalinski.kobweb.tabler.chart

      import org.w3c.dom.Element
      """.trimIndent(),
    )

    assertEquals(1, findings.size)
  }

  @Test
  fun `allows DOM imports in the foundation DSL`() {
    val findings = FoundationComposeOnlyRule(Config.empty).lint(
      """
      package com.github.jangalinski.kobweb.tabler._foundation.compose

      import org.jetbrains.compose.web.dom.Div
      import org.w3c.dom.Element
      """.trimIndent(),
    )

    assertEquals(0, findings.size)
  }

  @Test
  fun `ignores a legacy file with a deprecated top-level declaration`() {
    val findings = FoundationComposeOnlyRule(Config.empty).lint(
      """
      package com.github.jangalinski.kobweb.tabler.__trash

      import org.jetbrains.compose.web.dom.Div

      @Deprecated("Legacy API")
      class LegacyComponent
      """.trimIndent(),
    )

    assertEquals(0, findings.size)
  }
}
