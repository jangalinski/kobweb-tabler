package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler.models.TablerPaginationData
import com.github.jangalinski.kobweb.tabler.models.TablerTableColumn
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.models.TablerTableResponsive
import com.github.jangalinski.kobweb.tabler.models.TablerTableRows
import com.github.jangalinski.kobweb.tabler.styles.GridWidth
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.QUARTER

/**
 * Scoped builder for card-only Tabler grids.
 */
@TablerDsl
class TablerCardsScope internal constructor() {

  /**
   * Adds a regular Tabler card to the grid.
   */
  @Composable
  fun card(
    title: String? = null,
    width: GridWidth = GridWidth.FULL,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
  ) {
    Box(modifier = modifier.then(width.classNames.modifier())) {
      TablerCard(
        title = title,
        modifier = ClassNames.cardH100.modifier(),
        content = content,
      )
    }
  }

  /**
   * Adds a [TablerTableCard] to the grid, rendering the table flush to the card edges.
   *
   * @param pagination   optional pagination state; when set a `.card-footer` is rendered
   * @param onPageChange callback invoked with the 1-based target page number on page link clicks
   */
  @Composable
  fun tableCard(
    title: String,
    subtitle: String? = null,
    width: GridWidth = GridWidth.FULL,
    modifier: Modifier = Modifier,
    data: TablerTableData,
    pagination: TablerPaginationData? = null,
    onPageChange: ((Int) -> Unit)? = null,
  ) {
    Box(modifier = modifier.then(width.classNames.modifier())) {
      TablerTableCard(
        title = title,
        subtitle = subtitle,
        data = data,
        pagination = pagination,
        onPageChange = onPageChange,
      )
    }
  }

  /**
   * Adds a [TablerTableCard] backed by a row source that may manage pagination.
   *
   * @param columns visible column headings
   * @param rows row source to render; paginated sources also provide footer state
   * @param responsive breakpoint at which horizontal scrolling starts
   * @param noWrap prevents text wrapping in all cells when `true`
   * @param stickyHeader makes the header row stick to the viewport top when scrolling
   */
  @Composable
  fun tableCard(
    title: String,
    subtitle: String? = null,
    width: GridWidth = GridWidth.FULL,
    modifier: Modifier = Modifier,
    columns: List<TablerTableColumn>,
    rows: TablerTableRows,
    responsive: TablerTableResponsive = TablerTableResponsive.ALWAYS,
    noWrap: Boolean = false,
    stickyHeader: Boolean = false,
  ) {
    Box(modifier = modifier.then(width.classNames.modifier())) {
      TablerTableCard(
        title = title,
        subtitle = subtitle,
        columns = columns,
        rows = rows,
        responsive = responsive,
        noWrap = noWrap,
        stickyHeader = stickyHeader,
      )
    }
  }

  /**
   * Adds a compact stat card to the grid.
   */
  @Composable
  fun statCard(
    title: String,
    value: String,
    note: String? = null,
    badgeText: String? = null,
    width: GridWidth = QUARTER,
    modifier: Modifier = Modifier,
  ) {
    TablerStatCard(
      title = title,
      value = value,
      note = note,
      badgeText = badgeText,
      width = width,
      modifier = modifier,
    )
  }
}


/**
 * Lays out Tabler cards in a responsive deck-style grid.
 */
@Composable
fun TablerCards(
  modifier: Modifier = Modifier,
  content: @Composable TablerCardsScope.() -> Unit,
) {
  Row(
    modifier = modifier.then(
      ClassNames.row.modifier()
        .then(ClassNames.rowDeck.modifier())
        .then(ClassNames.rowCardsOnly.modifier())
        .then(ClassNames.g3.modifier())
        .then(ClassNames.mb4.modifier()),
    ),
    content = {
      TablerCardsScope().content()
    },
  )
}
