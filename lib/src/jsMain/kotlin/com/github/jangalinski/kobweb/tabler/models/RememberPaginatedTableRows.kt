package com.github.jangalinski.kobweb.tabler.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * Creates a remembered client-side paginated row source.
 *
 * The caller provides the full row list and a [pageSize]. The returned row source owns
 * current-page state, exposes the current page slice via [TablerTableRows.rows], and
 * exposes pagination metadata plus a [TablerTableRows.goToPage] transition handler.
 */
@Composable
fun rememberPaginatedTableRows(
  rows: List<TablerTableRow>,
  pageSize: Int,
  initialPage: Int = 1,
  texts: TablerPaginationTexts = TablerPaginationTexts(),
  window: TablerPaginationWindow = TablerPaginationWindow(),
): PaginatedTablerTableRows {
  require(pageSize > 0) { "pageSize must be > 0 (got $pageSize)" }

  val totalPages = maxOf(1, (rows.size + pageSize - 1) / pageSize)
  val currentPageState = remember { mutableStateOf(initialPage.coerceIn(1, totalPages)) }
  val currentPage = currentPageState.value.coerceIn(1, totalPages)

  return PaginatedTablerTableRows(
    allRows = rows,
    pageSize = pageSize,
    currentPage = currentPage,
    texts = texts,
    window = window,
    onPageSelected = { page -> currentPageState.value = page.coerceIn(1, totalPages) },
  )
}
