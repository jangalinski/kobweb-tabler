package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import org.jetbrains.compose.web.dom.Text
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerFooterTest {

  @Test
  fun rendersFooterClass() = runTest {
    composition {
      TablerFooter { }
    }

    val html = root.innerHTML
    assertThat(html).contains("footer")
  }

  @Test
  fun rendersSlotContent() = runTest {
    composition {
      TablerFooter {
        Text("© 2024 My App")
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("© 2024 My App")
  }

  @Test
  fun rendersFooterTransparentClass() = runTest {
    composition {
      TablerFooter { }
    }

    val html = root.innerHTML
    assertThat(html).contains("footer-transparent")
  }
}
