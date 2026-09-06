package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.models.TablerPaginationData
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.varabyte.kobweb.compose.foundation.layout.Column
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
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
          P(attrs = { attr("class", "${ClassNames.m0} ${ClassNames.textSecondary}") }) {
            Text("Showing ")
            Span(attrs = { attr("class", ClassNames.fwSemibold) }) { Text("$firstItem to $lastItem") }
            Text(" of ")
            Span(attrs = { attr("class", ClassNames.fwSemibold) }) { Text("$totalItems entries") }
          }
        }
      }
      Div(attrs = { attr("class", ClassNames.paginationLinksCol) }) {
        Ul(attrs = { attr("class", ClassNames.pagination) }) {
          PaginationItem(
            page = pagination.currentPage - 1,
            label = "\u00ab",
            disabled = pagination.currentPage <= 1,
            onPageChange = onPageChange,
          )
          (1..pagination.totalPages).forEach { page ->
            PaginationItem(
              page = page,
              label = page.toString(),
              active = page == pagination.currentPage,
              onPageChange = onPageChange,
            )
          }
          PaginationItem(
            page = pagination.currentPage + 1,
            label = "\u00bb",
            disabled = pagination.currentPage >= pagination.totalPages,
            onPageChange = onPageChange,
          )
        }
      }
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
