package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import assertk.assertions.isGreaterThanOrEqualTo
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerTrackingTest {

  @Test
  fun rendersTrackingContainerClass() = runTest {
    composition {
      TablerTracking(blocks = listOf(TrackingBlock()))
    }

    val html = root.innerHTML
    assertThat(html).contains("tracking")
  }

  @Test
  fun rendersOneBlockPerEntry() = runTest {
    val blocks = listOf(TrackingBlock(), TrackingBlock(), TrackingBlock())

    composition {
      TablerTracking(blocks = blocks)
    }

    val html = root.innerHTML
    assertThat(html).contains("tracking-block")
    // three divs with tracking-block class
    assertThat(html.split("tracking-block").size - 1).isGreaterThanOrEqualTo(3)
  }

  @Test
  fun rendersVariantClassWhenProvided() = runTest {
    composition {
      TablerTracking(
        blocks = listOf(
          TrackingBlock(variantClass = "bg-success"),
          TrackingBlock(variantClass = "bg-danger"),
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("bg-success")
    assertThat(html).contains("bg-danger")
  }

  @Test
  fun rendersTooltipAttributesWhenProvided() = runTest {
    composition {
      TablerTracking(blocks = listOf(TrackingBlock(tooltip = "All systems OK")))
    }

    val html = root.innerHTML
    assertThat(html).contains("data-bs-toggle")
    assertThat(html).contains("tooltip")
    assertThat(html).contains("All systems OK")
  }

  @Test
  fun omitsTooltipAttributesWhenNotProvided() = runTest {
    composition {
      TablerTracking(blocks = listOf(TrackingBlock()))
    }

    val html = root.innerHTML
    assertThat(html).doesNotContain("data-bs-toggle")
  }

  @Test
  fun rendersEmptyBlockListWithoutError() = runTest {
    composition {
      TablerTracking(blocks = emptyList())
    }

    val html = root.innerHTML
    assertThat(html).contains("tracking")
    assertThat(html).doesNotContain("tracking-block")
  }
}
