package com.github.jangalinski.kobweb.tabler.models

/**
 * Presentational pagination state for a [com.github.jangalinski.kobweb.tabler.components.TablerTableCard].
 *
 * The component itself is display-only: the caller is responsible for tracking the active page
 * and slicing the data accordingly. Prefer [PaginatedTablerTableRows] when the table should
 * own client-side page state and row slicing.
 *
 * @param currentPage 1-based index of the currently visible page
 * @param totalPages  total number of pages available
 * @param pageSize    optional — rows per page; when provided together with [totalItems] the
 *                    card footer shows a summary built from [TablerPaginationTexts.summaryTemplate]
 * @param totalItems  optional — total number of items across all pages; used with [pageSize]
 *                    for the summary text
 * @param texts       visible labels used by the pagination footer
 */
data class TablerPaginationData(
  val currentPage: Int,
  val totalPages: Int,
  val pageSize: Int? = null,
  val totalItems: Int? = null,
  val texts: TablerPaginationTexts = TablerPaginationTexts(),
) {
  init {
    require(currentPage >= 1) { "currentPage must be >= 1 (got $currentPage)" }
    require(totalPages >= 1) { "totalPages must be >= 1 (got $totalPages)" }
    require(currentPage <= totalPages) { "currentPage ($currentPage) must be <= totalPages ($totalPages)" }
    pageSize?.let { require(it > 0) { "pageSize must be > 0 when set (got $it)" } }
    totalItems?.let { require(it >= 0) { "totalItems must be >= 0 when set (got $it)" } }
  }
}

/**
 * Display text used by a table pagination footer.
 *
 * This keeps pagination presentational text configurable while leaving page calculation
 * and row slicing to [PaginatedTablerTableRows].
 *
 * @param previousPageLabel visible label for the previous-page link
 * @param nextPageLabel visible label for the next-page link
 * @param summaryTemplate summary text template. `{index}` is replaced by the visible
 *                        item index or range, and `{max}` is replaced by the total item count.
 */
data class TablerPaginationTexts(
  val previousPageLabel: String = "\u00ab",
  val nextPageLabel: String = "\u00bb",
  val summaryTemplate: String = "Showing {index} of {max} entries",
)
