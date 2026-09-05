package com.github.jangalinski.kobweb.tabler.components

import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import org.jetbrains.compose.web.dom.Text
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerCardTest {
  @Test
  fun rendersTitleAndBodyUsingTablerStructure() = runTest {
    composition {
      TablerCard(title = "Card title") {
        Text("Card body")
      }
    }

    val html = root.innerHTML
    println(html)
    assertTrue(html.contains("class=\"card "))
    assertTrue(html.contains("class=\"card-header "))
    assertTrue(html.contains("class=\"card-title\""))
    assertTrue(html.contains("Card title"))
    assertTrue(html.contains("class=\"card-body "))
    assertTrue(html.contains("Card body"))
  }
}
