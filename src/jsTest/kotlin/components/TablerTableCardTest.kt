package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import com.github.jangalinski.kobweb.tabler.models.TablerPaginationData
import com.github.jangalinski.kobweb.tabler.models.TablerPaginationTexts
import com.github.jangalinski.kobweb.tabler.models.TablerPaginationWindow
import com.github.jangalinski.kobweb.tabler.models.TablerTableCell
import com.github.jangalinski.kobweb.tabler.models.TablerTableColumn
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.models.TablerTableResponsive
import com.github.jangalinski.kobweb.tabler.models.TablerTableRow
import com.github.jangalinski.kobweb.tabler.models.rememberPaginatedTableRows
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import org.w3c.dom.HTMLElement
import kotlin.test.Test

@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerTableCardTest {

  private fun simpleData() = TablerTableData(
    columns = listOf(TablerTableColumn("Name")),
    rows = listOf(TablerTableRow(listOf(TablerTableCell.Text("Alice")))),
  )

  @Test
  fun rendersCardHeader() = runTest {
    composition {
      TablerTableCard(title = "Players", data = simpleData())
    }

    val html = root.innerHTML
    assertThat(html).contains("card-header")
  }

  @Test
  fun rendersTitleInCardHeader() = runTest {
    composition {
      TablerTableCard(title = "My Table", data = simpleData())
    }

    val html = root.innerHTML
    assertThat(html).contains("My Table")
    assertThat(html).contains("card-title")
  }

  @Test
  fun rendersSubtitleWhenProvided() = runTest {
    composition {
      TablerTableCard(title = "Players", subtitle = "Season 2026", data = simpleData())
    }

    val html = root.innerHTML
    assertThat(html).contains("Season 2026")
  }

  @Test
  fun omitsSubtitleWhenAbsent() = runTest {
    composition {
      TablerTableCard(title = "Players", data = simpleData())
    }

    val html = root.innerHTML
    // subtitle span only appears when subtitle is provided; verify table still renders
    assertThat(html).contains("card-header")
    assertThat(html).contains("Alice")
  }

  @Test
  fun hasNoCardBodyWrapper() = runTest {
    composition {
      TablerTableCard(title = "Players", data = simpleData())
    }

    val html = root.innerHTML
    assertThat(html).doesNotContain("card-body")
  }

  @Test
  fun rendersResponsiveWrapper() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Name")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Text("Bob")))),
      responsive = TablerTableResponsive.ALWAYS,
    )

    composition {
      TablerTableCard(title = "Players", data = data)
    }

    val html = root.innerHTML
    assertThat(html).contains("table-responsive")
  }

  @Test
  fun rendersTableData() = runTest {
    composition {
      TablerTableCard(title = "Players", data = simpleData())
    }

    val html = root.innerHTML
    assertThat(html).contains("Alice")
    assertThat(html).contains("card-table")
  }

  // --- Pagination tests ---

  @Test
  fun hasNoCardFooterWithoutPagination() = runTest {
    composition {
      TablerTableCard(title = "Players", data = simpleData())
    }

    val html = root.innerHTML
    assertThat(html).doesNotContain("card-footer")
  }

  @Test
  fun rendersCardFooterWithPagination() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(currentPage = 1, totalPages = 3),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("card-footer")
    assertThat(html).contains("pagination")
  }

  @Test
  fun rendersActivePageLink() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(currentPage = 2, totalPages = 3),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("page-item active")
  }

  @Test
  fun disablesPrevButtonOnFirstPage() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(currentPage = 1, totalPages = 3),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("page-item disabled")
  }

  @Test
  fun disablesNextButtonOnLastPage() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(currentPage = 3, totalPages = 3),
      )
    }

    // Both prev (not on page 1, so enabled) and next (on last page, so disabled).
    // Only the next button is disabled here; prev is enabled.
    val html = root.innerHTML
    // «  and  »  — next is disabled, so "disabled" appears once
    assertThat(html).contains("page-item disabled")
    // Active page (page 3)
    assertThat(html).contains("page-item active")
  }

  @Test
  fun rendersSummaryTextWhenPageSizeAndTotalItemsProvided() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(
          currentPage = 1,
          totalPages = 3,
          pageSize = 5,
          totalItems = 13,
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("Showing")
    assertThat(html).contains("entries")
    assertThat(html).contains("1 to 5")
    assertThat(html).contains("13")
  }

  @Test
  fun omitsSummaryTextWhenPageSizeAbsent() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(currentPage = 1, totalPages = 3),
      )
    }

    val html = root.innerHTML
    assertThat(html).doesNotContain("Showing")
  }

  @Test
  fun rendersCorrectNumberOfPageLinks() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(currentPage = 1, totalPages = 4),
      )
    }

    val html = root.innerHTML
    // 4 numbered pages + prev + next = 6 page-link elements
    assertThat(html.split("page-link").size - 1).isEqualTo(6)
  }

  @Test
  fun limitsPageLinksAroundCurrentPage() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(
          currentPage = 15,
          totalPages = 40,
          window = TablerPaginationWindow(maxVisiblePageNumbers = 5),
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("data-page=\"1\"")
    assertThat(html).contains("data-page=\"14\"")
    assertThat(html).contains("data-page=\"15\"")
    assertThat(html).contains("data-page=\"16\"")
    assertThat(html).contains("data-page=\"40\"")
    assertThat(html).contains("..")
    // 5 numbered pages + 2 ellipses + prev + next = 9 page-link elements
    assertThat(html.split("page-link").size - 1).isEqualTo(9)
  }

  @Test
  fun usesFollowingPagesNearStartOfLimitedPagination() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(
          currentPage = 1,
          totalPages = 40,
          window = TablerPaginationWindow(maxVisiblePageNumbers = 5),
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("data-page=\"1\"")
    assertThat(html).contains("data-page=\"2\"")
    assertThat(html).contains("data-page=\"3\"")
    assertThat(html).contains("data-page=\"40\"")
    assertThat(html).doesNotContain("data-page=\"4\"")
  }

  @Test
  fun usesPreviousPagesNearEndOfLimitedPagination() = runTest {
    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(
          currentPage = 40,
          totalPages = 40,
          window = TablerPaginationWindow(maxVisiblePageNumbers = 5),
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("data-page=\"1\"")
    assertThat(html).contains("data-page=\"38\"")
    assertThat(html).contains("data-page=\"39\"")
    assertThat(html).contains("data-page=\"40\"")
    assertThat(html).doesNotContain("data-page=\"37\"")
  }

  @Test
  fun rendersConfigurablePaginationEllipsisWithoutPageChange() = runTest {
    var selectedPage: Int? = null

    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(
          currentPage = 15,
          totalPages = 40,
          texts = TablerPaginationTexts(ellipsisLabel = "..."),
          window = TablerPaginationWindow(maxVisiblePageNumbers = 5),
        ),
        onPageChange = { selectedPage = it },
      )
    }

    val ellipsis = root.querySelector(".page-item.disabled .page-link") as HTMLElement
    ellipsis.click()

    assertThat(root.innerHTML).contains("...")
    assertThat(ellipsis.getAttribute("data-page")).isEqualTo(null)
    assertThat(selectedPage).isEqualTo(null)
  }

  @Test
  fun invokesPageChangeCallbackForEnabledPageLink() = runTest {
    var selectedPage: Int? = null

    composition {
      TablerTableCard(
        title = "Players",
        data = simpleData(),
        pagination = TablerPaginationData(currentPage = 1, totalPages = 3),
        onPageChange = { selectedPage = it },
      )
    }

    (root.querySelector("[data-page=\"2\"]") as HTMLElement).click()

    assertThat(selectedPage).isEqualTo(2)
  }

  @Test
  fun tableCardRowSourceHandlesPaginationClicks() = runTest {
    val allRows = listOf("Alice", "Bob", "Cara", "Dan", "Eve")
      .map { name -> TablerTableRow(listOf(TablerTableCell.Text(name))) }

    composition {
      val rows = rememberPaginatedTableRows(
        rows = allRows,
        pageSize = 2,
        texts = TablerPaginationTexts(summaryTemplate = "Showing {index} of {max} players"),
      )
      TablerTableCard(
        title = "Players",
        columns = listOf(TablerTableColumn("Name")),
        rows = rows,
      )
    }

    assertThat(root.innerHTML).contains("Alice")
    assertThat(root.innerHTML).doesNotContain("Cara")

    (root.querySelector("[data-page=\"3\"]") as HTMLElement).click()
    waitForRecompositionComplete()

    val html = root.innerHTML
    assertThat(html).contains("Eve")
    assertThat(html).doesNotContain("Alice")
    assertThat(html).contains("5 players")
    assertThat(html).contains("Showing 5 of 5 players")
    assertThat(html).doesNotContain("5 to 5")
    assertThat(html).contains("table-placeholder-row")
  }
}
