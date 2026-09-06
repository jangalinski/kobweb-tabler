package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import org.jetbrains.compose.web.dom.Text
import kotlin.test.Test

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
    assertThat(html).contains("class=\"card ")
    assertThat(html).contains("class=\"card-header ")
    assertThat(html).contains("class=\"card-title\"")
    assertThat(html).contains("Card title")
    assertThat(html).contains("class=\"card-body ")
    assertThat(html).contains("Card body")
  }
}
