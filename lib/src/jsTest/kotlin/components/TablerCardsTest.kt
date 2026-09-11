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
class TablerCardsTest {

  @Test
  fun rendersRowDeckClasses() = runTest {
    composition {
      TablerCards { }
    }

    val html = root.innerHTML
    assertThat(html).contains("row-deck")
    assertThat(html).contains("row-cards")
  }

  @Test
  fun rendersCardViaScope() = runTest {
    composition {
      TablerCards {
        card(title = "My Card") {
          Text("Card content")
        }
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("My Card")
    assertThat(html).contains("Card content")
    assertThat(html).contains("class=\"card ")
  }

  @Test
  fun rendersStatCardViaScope() = runTest {
    composition {
      TablerCards {
        statCard(title = "Revenue", value = "€ 5 000")
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("Revenue")
    assertThat(html).contains("€ 5 000")
  }

  @Test
  fun rendersMultipleCardsViaScope() = runTest {
    composition {
      TablerCards {
        card(title = "First") { Text("A") }
        card(title = "Second") { Text("B") }
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("First")
    assertThat(html).contains("Second")
    assertThat(html).contains("A")
    assertThat(html).contains("B")
  }
}
