package com.github.jangalinski.kobweb.tabler.button

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import com.github.jangalinski.kobweb.tabler.KobwebTabler.button
import com.github.jangalinski.kobweb.tabler.KobwebTabler.buttons
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test

@OptIn(ComposeWebExperimentalTestsApi::class)
class ButtonTest {

  @Test
  fun rendersInstancesAndDslButtonsWithSupportedVariants() = runTest {
    composition {
      Button(text = "Primary", color = ButtonColor.PRIMARY)()
      button(text = "Outline", color = ButtonColor.GREEN, style = ButtonStyle.OUTLINE, size = ButtonSize.LARGE)
      buttons {
        button(text = "Pill", color = ButtonColor.PURPLE, shape = ButtonShape.PILL)
        button(text = "Right", icon = TablerIcon.TI_ARROW_RIGHT, iconPosition = ButtonIconPosition.RIGHT)
      }
    }

    val html = root.innerHTML

    assertThat(html).contains("btn")
    assertThat(html).contains("btn-primary")
    assertThat(html).contains("btn-outline")
    assertThat(html).contains("btn-green")
    assertThat(html).contains("btn-lg")
    assertThat(html).contains("btn-list")
    assertThat(html).contains("btn-pill")
    assertThat(html).contains("ti-arrow-right")
    assertThat(root.querySelectorAll("button.btn").length).isEqualTo(4)
  }

  @Test
  fun rendersAccessibleIconOnlyAndLoadingButtons() = runTest {
    composition {
      Button(icon = TablerIcon.TI_STAR, ariaLabel = "Favorite", color = ButtonColor.YELLOW)()
      Button(text = "Saving", loading = true)()
    }

    val html = root.innerHTML

    assertThat(html).contains("btn-icon")
    assertThat(html).contains("aria-label=\"Favorite\"")
    assertThat(html).contains("btn-loading")
    assertThat(html).contains("aria-busy=\"true\"")
    assertThat(root.querySelectorAll("button[disabled]").length).isEqualTo(1)
  }
}
