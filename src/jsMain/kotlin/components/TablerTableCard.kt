package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.models.TablerPaginationData
import com.github.jangalinski.kobweb.tabler.models.TablerTableColumn
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.models.TablerTableResponsive
import com.github.jangalinski.kobweb.tabler.models.TablerTableRows
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.varabyte.kobweb.compose.foundation.layout.Column
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.w3c.dom.HTMLDivElement

/**
 * Renders a Tabler card that displays a data table flush against the card edges.
 *
 * Unlike [TablerCard], this composable omits the `.card-body` wrapper and places the
 * responsive table directly inside the `.card`, matching Tabler's `card-table` pattern.
 * This avoids the extra padding gap that would appear if the table were placed inside a
 * normal card body.
 *
 * When [pagination] is provided a `.card-footer` is rendered below the table with:
 * - a "Showing X to Y of Z entries" summary (when [TablerPaginationData.pageSize] and
 *   [TablerPaginationData.totalItems] are set), and
 * - numbered page links with previous/next chevron buttons.
 *
 * The component is **presentational only**: the caller owns the active-page state and
 * reacts to page changes via [onPageChange].
 *
 * @param title        headline shown in the `.card-header`
 * @param subtitle     optional secondary line shown below the title inside the card header
 * @param data         pure-data table configuration forwarded to [TablerTable]
 * @param pagination   optional pagination state; omit to hide the footer entirely
 * @param onPageChange optional callback invoked with the 1-based target page number when
 *                     the user clicks a pagination link
 */
@Composable
fun TablerTableCard(
  title: String,
  subtitle: String? = null,
  data: TablerTableData,
  pagination: TablerPaginationData? = null,
  onPageChange: ((Int) -> Unit)? = null,
) {
  Column(modifier = ClassNames.card.modifier()) {
    Div(attrs = { attr("class", ClassNames.cardHeader) }) {
      Div {
        H3(attrs = { attr("class", ClassNames.cardTitle) }) {
          Text(title)
        }
        subtitle?.let {
          Div(attrs = { attr("class", "${ClassNames.textSecondary} ${ClassNames.mt1}") }) {
            Text(it)
          }
        }
      }
    }
    // Table rendered without its own responsive wrapper; the Div below provides it.
    Div(attrs = {
      data.responsive.className?.let(::classes)
      prop({ element: HTMLDivElement, markup: String -> element.innerHTML = markup }, renderTableMarkup(data))
    })
    pagination?.let { pag ->
      PaginationFooter(pagination = pag, onPageChange = onPageChange)
    }
  }
}

/**
 * Renders a table card from a row source that may own table behaviour such as pagination.
 *
 * Static row sources render all rows directly. Paginated row sources provide their current
 * page slice, footer metadata, and page-change handler so callers do not need to repeat
 * pagination calculations in page code.
 *
 * @param title headline shown in the `.card-header`
 * @param subtitle optional secondary line shown below the title inside the card header
 * @param columns visible column headings
 * @param rows row source to render; paginated sources also provide footer state
 * @param responsive breakpoint at which horizontal scrolling starts
 * @param noWrap prevents text wrapping in all cells when `true`
 * @param stickyHeader makes the header row stick to the viewport top when scrolling
 */
@Composable
fun TablerTableCard(
  title: String,
  subtitle: String? = null,
  columns: List<TablerTableColumn>,
  rows: TablerTableRows,
  responsive: TablerTableResponsive = TablerTableResponsive.ALWAYS,
  noWrap: Boolean = false,
  stickyHeader: Boolean = false,
) {
  TablerTableCard(
    title = title,
    subtitle = subtitle,
    data = TablerTableData(
      columns = columns,
      rows = rows,
      responsive = responsive,
      noWrap = noWrap,
      stickyHeader = stickyHeader,
    ),
    pagination = rows.pagination,
    onPageChange = rows::goToPage,
  )
}

@Composable
private fun PaginationFooter(
  pagination: TablerPaginationData,
  onPageChange: ((Int) -> Unit)?,
) {
  Div(attrs = { attr("class", ClassNames.cardFooter) }) {
    Div(attrs = { attr("class", ClassNames.paginationRow) }) {
      Div(attrs = { attr("class", ClassNames.paginationSummaryCol) }) {
        val pageSize = pagination.pageSize
        val totalItems = pagination.totalItems
        if (pageSize != null && totalItems != null) {
          val firstItem = if (totalItems == 0) 0 else (pagination.currentPage - 1) * pageSize + 1
          val lastItem = minOf(pagination.currentPage * pageSize, totalItems)
          val index = if (firstItem == lastItem) firstItem.toString() else "$firstItem to $lastItem"
          P(attrs = { attr("class", "${ClassNames.m0} ${ClassNames.textSecondary}") }) {
            Text(pagination.texts.summaryTemplate.replace("{index}", index).replace("{max}", totalItems.toString()))
          }
        }
      }
      Div(attrs = { attr("class", ClassNames.paginationLinksCol) }) {
        Ul(attrs = { attr("class", ClassNames.pagination) }) {
          PaginationItem(
            page = pagination.currentPage - 1,
            label = pagination.texts.previousPageLabel,
            disabled = pagination.currentPage <= 1,
            onPageChange = onPageChange,
          )
          paginationTokens(pagination).forEach { token ->
            when (token) {
              PaginationToken.Ellipsis -> PaginationEllipsis(pagination.texts.ellipsisLabel)
              is PaginationToken.Page -> PaginationItem(
                page = token.page,
                label = token.page.toString(),
                active = token.page == pagination.currentPage,
                onPageChange = onPageChange,
              )
            }
          }
          PaginationItem(
            page = pagination.currentPage + 1,
            label = pagination.texts.nextPageLabel,
            disabled = pagination.currentPage >= pagination.totalPages,
            onPageChange = onPageChange,
          )
        }
      }
    }
  }
}

private sealed interface PaginationToken {
  data class Page(val page: Int) : PaginationToken
  data object Ellipsis : PaginationToken
}

private fun paginationTokens(pagination: TablerPaginationData): List<PaginationToken> {
  val maxVisiblePageNumbers = pagination.window.maxVisiblePageNumbers
    ?: return (1..pagination.totalPages).map(PaginationToken::Page)

  if (pagination.totalPages <= maxVisiblePageNumbers) {
    return (1..pagination.totalPages).map(PaginationToken::Page)
  }

  val boundaryCount = pagination.window.boundaryCount
  val siblingCount = pagination.window.siblingCount
  val visiblePages = mutableSetOf<Int>()

  (1..boundaryCount).forEach { page ->
    if (page in 1..pagination.totalPages) visiblePages += page
  }
  ((pagination.totalPages - boundaryCount + 1)..pagination.totalPages).forEach { page ->
    if (page in 1..pagination.totalPages) visiblePages += page
  }
  val currentWindowSize = siblingCount * 2 + 1
  var currentWindowStart = pagination.currentPage - siblingCount
  var currentWindowEnd = pagination.currentPage + siblingCount
  if (currentWindowStart < 1) {
    currentWindowEnd += 1 - currentWindowStart
    currentWindowStart = 1
  }
  if (currentWindowEnd > pagination.totalPages) {
    currentWindowStart -= currentWindowEnd - pagination.totalPages
    currentWindowEnd = pagination.totalPages
  }
  currentWindowStart = currentWindowStart.coerceAtLeast(1)
  currentWindowEnd = currentWindowEnd.coerceAtMost(pagination.totalPages)
  if (currentWindowEnd - currentWindowStart + 1 > currentWindowSize) {
    currentWindowEnd = currentWindowStart + currentWindowSize - 1
  }
  (currentWindowStart..currentWindowEnd).forEach { page ->
    if (page in 1..pagination.totalPages) visiblePages += page
  }

  return visiblePages.sorted()
    .fold(mutableListOf<PaginationToken>()) { tokens, page ->
      val previousPage = (tokens.lastOrNull() as? PaginationToken.Page)?.page
      if (previousPage != null && page - previousPage > 1) {
        tokens += PaginationToken.Ellipsis
      }
      tokens += PaginationToken.Page(page)
      tokens
    }
}

@Composable
private fun PaginationEllipsis(label: String) {
  Li(attrs = { attr("class", ClassNames.pageItemDisabled) }) {
    A(attrs = {
      attr("class", ClassNames.pageLink)
      attr("href", "#")
      attr("tabindex", "-1")
      attr("aria-disabled", "true")
      onClick {
        it.preventDefault()
      }
    }) {
      Text(label)
    }
  }
}

@Composable
private fun PaginationItem(
  page: Int,
  label: String,
  disabled: Boolean = false,
  active: Boolean = false,
  onPageChange: ((Int) -> Unit)?,
) {
  Li(attrs = {
    attr("class", when {
      disabled -> ClassNames.pageItemDisabled
      active -> ClassNames.pageItemActive
      else -> ClassNames.pageItem
    })
  }) {
    A(attrs = {
      attr("class", ClassNames.pageLink)
      attr("href", "#")
      if (disabled) {
        attr("tabindex", "-1")
        attr("aria-disabled", "true")
        onClick {
          it.preventDefault()
        }
      } else {
        attr("data-page", page.toString())
        onClick {
          it.preventDefault()
          onPageChange?.invoke(page)
        }
      }
    }) {
      Text(label)
    }
  }
}
