package com.github.jangalinski.kobweb.tabler.models

/**
 * Presentational pagination state for a [com.github.jangalinski.kobweb.tabler.components.TablerTableCard].
 *
 * The component itself is display-only: the caller is responsible for tracking the active page
 * and slicing the data accordingly. Use [onPageChange] on the card to react to user clicks.
 *
 * @param currentPage 1-based index of the currently visible page
 * @param totalPages  total number of pages available
 * @param pageSize    optional — rows per page; when provided together with [totalItems] the
 *                    card footer shows a "Showing X to Y of Z entries" summary
 * @param totalItems  optional — total number of items across all pages; used with [pageSize]
 *                    for the summary text
 */
data class TablerPaginationData(
  val currentPage: Int,
  val totalPages: Int,
  val pageSize: Int? = null,
  val totalItems: Int? = null,
) {
  init {
    require(currentPage >= 1) { "currentPage must be >= 1 (got $currentPage)" }
    require(totalPages >= 1) { "totalPages must be >= 1 (got $totalPages)" }
    require(currentPage <= totalPages) { "currentPage ($currentPage) must be <= totalPages ($totalPages)" }
    pageSize?.let { require(it > 0) { "pageSize must be > 0 when set (got $it)" } }
    totalItems?.let { require(it >= 0) { "totalItems must be >= 0 when set (got $it)" } }
  }
}
