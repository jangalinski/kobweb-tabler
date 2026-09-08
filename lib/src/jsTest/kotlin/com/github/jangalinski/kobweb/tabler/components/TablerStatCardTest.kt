package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test

@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerStatCardTest {

  @Test
  fun rendersTitleAndValue() = runTest {
    composition {
      TablerStatCard(title = "Users", value = "1 234")
    }

    val html = root.innerHTML
    assertThat(html).contains("Users")
    assertThat(html).contains("1 234")
    assertThat(html).contains("class=\"card")
  }

  @Test
  fun rendersOptionalNote() = runTest {
    composition {
      TablerStatCard(title = "Sales", value = "42", note = "Since last week")
    }

    val html = root.innerHTML
    assertThat(html).contains("Since last week")
  }

  @Test
  fun omitsNoteWhenNotProvided() = runTest {
    composition {
      TablerStatCard(title = "Sales", value = "42")
    }

    val html = root.innerHTML
    assertThat(html).doesNotContain("Since last week")
  }

  @Test
  fun rendersOptionalBadge() = runTest {
    composition {
      TablerStatCard(title = "Revenue", value = "€ 9 000", badgeText = "+12%")
    }

    val html = root.innerHTML
    assertThat(html).contains("+12%")
    assertThat(html).contains("badge")
  }

  @Test
  fun omitsBadgeWhenNotProvided() = runTest {
    composition {
      TablerStatCard(title = "Revenue", value = "€ 9 000")
    }

    val html = root.innerHTML
    assertThat(html).doesNotContain("badge")
  }
}
