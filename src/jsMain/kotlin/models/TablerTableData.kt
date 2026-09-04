package com.github.jangalinski.kobweb.tabler.models

/** Pure configuration for a [com.github.jangalinski.kobweb.tabler.components.TablerTable]. */
data class TablerTableData(
  val columns: List<TablerTableColumn>,
  val rows: List<TablerTableRow>,
  val responsive: TablerTableResponsive = TablerTableResponsive.ALWAYS,
  val noWrap: Boolean = false,
  val stickyHeader: Boolean = false,
) {
  init {
    require(columns.isNotEmpty()) { "A Tabler table requires at least one column." }
    require(rows.all { it.cells.size == columns.size }) {
      "Every Tabler table row must have the same number of cells as there are columns."
    }
  }
}

/** A visible column heading. */
data class TablerTableColumn(
  val label: String,
  val noWrap: Boolean = false,
)

/** One table row, optionally styled with a Tabler semantic variant. */
data class TablerTableRow(
  val cells: List<TablerTableCell>,
  val variant: TablerTableRowVariant? = null,
)

/** Text content in one table cell. */
data class TablerTableCell(
  val text: String,
  val muted: Boolean = false,
  val isRowHeader: Boolean = false,
)

/** Breakpoint at which a table stops scrolling horizontally. */
enum class TablerTableResponsive(internal val className: String?) {
  NONE(null),
  ALWAYS("table-responsive"),
  SMALL("table-responsive-sm"),
  MEDIUM("table-responsive-md"),
  LARGE("table-responsive-lg"),
  EXTRA_LARGE("table-responsive-xl"),
}

/** Tabler semantic row backgrounds. */
enum class TablerTableRowVariant(internal val className: String) {
  PRIMARY("table-primary"),
  SECONDARY("table-secondary"),
  SUCCESS("table-success"),
  DANGER("table-danger"),
  WARNING("table-warning"),
  INFO("table-info"),
  LIGHT("table-light"),
  DARK("table-dark"),
}
