package com.github.jangalinski.kobweb.tabler.table

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.table.TablerPaginationData
import com.github.jangalinski.kobweb.tabler.table.TablerTableColumn
import com.github.jangalinski.kobweb.tabler.table.TablerTableData
import com.github.jangalinski.kobweb.tabler.table.TablerTableResponsive
import com.github.jangalinski.kobweb.tabler.table.TablerTableRows
import com.github.jangalinski.kobweb.tabler._foundation.css.ClassNames
import com.github.jangalinski.kobweb.tabler._foundation.css.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler._foundation.compose.KAnchor
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.compose.KH3
import com.github.jangalinski.kobweb.tabler._foundation.compose.KHtmlDiv
import com.github.jangalinski.kobweb.tabler._foundation.compose.KLi
import com.github.jangalinski.kobweb.tabler._foundation.compose.KP
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.github.jangalinski.kobweb.tabler._foundation.compose.KUl
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.attr

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
    KDiv(modifier = ClassNames.cardHeader.modifier()) {
      KDiv {
        KH3(modifier = ClassNames.cardTitle.modifier()) {
          KText(title)
        }
        subtitle?.let {
          KDiv(modifier = "${ClassNames.textSecondary} ${ClassNames.mt1}".modifier()) {
            KText(it)
          }
        }
      }
    }
    // Table rendered without its own responsive wrapper; the Div below provides it.
    KHtmlDiv(
      html = renderTableMarkup(data),
      modifier = data.responsive.className?.modifier() ?: Modifier,
    )
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
  KDiv(modifier = ClassNames.cardFooter.modifier()) {
    KDiv(modifier = ClassNames.paginationRow.modifier()) {
      KDiv(modifier = ClassNames.paginationSummaryCol.modifier()) {
        val pageSize = pagination.pageSize
        val totalItems = pagination.totalItems
        if (pageSize != null && totalItems != null) {
          val firstItem = if (totalItems == 0) 0 else (pagination.currentPage - 1) * pageSize + 1
          val lastItem = minOf(pagination.currentPage * pageSize, totalItems)
          val index = if (firstItem == lastItem) firstItem.toString() else "$firstItem to $lastItem"
          KP(modifier = "${ClassNames.m0} ${ClassNames.textSecondary}".modifier()) {
            KText(pagination.texts.summaryTemplate.replace("{index}", index).replace("{max}", totalItems.toString()))
          }
        }
      }
      KDiv(modifier = ClassNames.paginationLinksCol.modifier()) {
        KUl(modifier = ClassNames.pagination.modifier()) {
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
  KLi(modifier = ClassNames.pageItemDisabled.modifier()) {
    KAnchor(
      href = "#",
      modifier = ClassNames.pageLink.modifier()
        .then(Modifier.attr("tabindex", "-1"))
        .then(Modifier.attr("aria-disabled", "true")),
      onClickAction = {},
    ) {
      KText(label)
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
  KLi(
    modifier = when {
      disabled -> ClassNames.pageItemDisabled.modifier()
      active -> ClassNames.pageItemActive.modifier()
      else -> ClassNames.pageItem.modifier()
    },
  ) {
    KAnchor(
      href = "#",
      modifier = ClassNames.pageLink.modifier()
        .then(if (disabled) Modifier.attr("tabindex", "-1") else Modifier)
        .then(if (disabled) Modifier.attr("aria-disabled", "true") else Modifier.attr("data-page", page.toString())),
      onClickAction = if (disabled) ({}) else ({ onPageChange?.invoke(page) }),
    ) {
      KText(label)
    }
  }
}
