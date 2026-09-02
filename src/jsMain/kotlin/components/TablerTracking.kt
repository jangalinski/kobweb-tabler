package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.Element
import kotlin.random.Random

private external interface TablerTooltip {
  fun dispose()
}

private fun createTooltips(container: Element): Array<TablerTooltip> = js(
  """
    (() => {
      const Tooltip = window.tabler && window.tabler.Tooltip;
      return Tooltip
        ? Array.from(container.querySelectorAll('[data-bs-toggle="tooltip"]'))
            .map((element) => Tooltip.getOrCreateInstance(element))
        : [];
    })()
  """,
) as Array<TablerTooltip>

/**
 * One time slice in a [TablerTracking] activity display.
 *
 * A null [variantClass] renders the neutral Tabler state. Pass a Tabler background class, such as
 * `bg-success`, `bg-warning`, or `bg-danger`, to communicate the state of the time slice.
 */
data class TrackingBlock(
  val tooltip: String? = null,
  val variantClass: String? = null,
)

/**
 * Renders Tabler's compact activity-monitoring display.
 *
 * When a [TrackingBlock.tooltip] is supplied, the block is configured for Tabler's top-positioned
 * Bootstrap tooltip and retains its native `title` text as a non-JavaScript fallback.
 */
@Composable
fun TablerTracking(
  blocks: List<TrackingBlock>,
  modifier: Modifier = Modifier,
) {
  val trackingId = remember { "tabler-tracking-${Random.nextInt(Int.MAX_VALUE).toUInt()}" }

  DisposableEffect(trackingId, blocks) {
    val tooltips = document.getElementById(trackingId)?.let(::createTooltips).orEmpty()
    onDispose { tooltips.forEach(TablerTooltip::dispose) }
  }

  Box(modifier = modifier.id(trackingId).then(ClassNames.tracking.modifier())) {
    blocks.forEach { block ->
      Div(
        attrs = {
          classes(ClassNames.trackingBlock)
          block.variantClass?.let(::classes)
          block.tooltip?.let { tooltip ->
            attr("data-bs-toggle", "tooltip")
            attr("data-bs-placement", "top")
            attr("title", tooltip)
          }
        },
      )
    }
  }
}
