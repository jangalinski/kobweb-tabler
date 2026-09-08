package com.github.jangalinski.kobweb.tabler.models

/**
 * Pure configuration for a Tabler table.
 *
 * @param columns visible column headings
 * @param rows table body rows to render
 * @param responsive breakpoint at which horizontal scrolling starts
 * @param noWrap prevents text wrapping in all cells when `true`
 * @param stickyHeader makes the header row stick to the viewport top when scrolling
 * @param expectedDisplayRows optional body-row count to reserve visually with placeholder
 *                            rows when [rows] is shorter
 */
data class TablerTableData(
  val columns: List<TablerTableColumn>,
  val rows: List<TablerTableRow>,
  val responsive: TablerTableResponsive = TablerTableResponsive.ALWAYS,
  val noWrap: Boolean = false,
  val stickyHeader: Boolean = false,
  val expectedDisplayRows: Int? = null,
) {
  /**
   * Creates table data from a [TablerTableRows] source.
   *
   * This constructor keeps pagination-owned row slicing and expected display height with
   * the row source while preserving the normal [TablerTableData] rendering path.
   */
  constructor(
    columns: List<TablerTableColumn>,
    rows: TablerTableRows,
    responsive: TablerTableResponsive = TablerTableResponsive.ALWAYS,
    noWrap: Boolean = false,
    stickyHeader: Boolean = false,
  ) : this(
    columns = columns,
    rows = rows.rows,
    responsive = responsive,
    noWrap = noWrap,
    stickyHeader = stickyHeader,
    expectedDisplayRows = rows.expectedDisplayRows,
  )

  init {
    require(columns.isNotEmpty()) { "A Tabler table requires at least one column." }
    require(rows.all { it.cells.size == columns.size }) {
      "Every Tabler table row must have the same number of cells as there are columns."
    }
    require(expectedDisplayRows == null || expectedDisplayRows >= 0) {
      "expectedDisplayRows must be >= 0 when set."
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

/**
 * Sealed content model for a single Tabler table cell.
 *
 * Use [Text] for the common plain-text case, or one of the richer subtypes
 * ([AvatarName], [Badge], [Tags], [Checkbox]) for structured content.
 */
sealed interface TablerTableCell {
  /** Whether this cell acts as a row header (`<th scope="row">` instead of `<td>`). */
  val isRowHeader: Boolean get() = false

  /**
   * A plain-text cell, optionally muted and/or acting as a row header.
   *
   * This is the direct replacement for the former `TablerTableCell` data class.
   */
  data class Text(
    val value: String,
    val muted: Boolean = false,
    override val isRowHeader: Boolean = false,
  ) : TablerTableCell

  /**
   * A cell containing a small avatar image followed by a display name.
   *
   * The avatar is rendered using the same markup conventions as Tabler avatars.
   */
  data class AvatarName(
    val avatar: TablerAvatarData,
    val name: String,
  ) : TablerTableCell

  /**
   * A cell containing a Tabler badge / status chip.
   *
   * @param label  visible badge text
   * @param variant optional Bootstrap/Tabler colour modifier, e.g. `"bg-success"` or `"badge-outline text-green"`
   */
  data class Badge(
    val label: String,
    val variant: String? = null,
  ) : TablerTableCell

  /**
   * A cell containing a horizontal list of tag spans.
   *
   * An empty [tags] list renders an empty cell without error.
   */
  data class Tags(val tags: List<String>) : TablerTableCell

  /**
   * A cell containing a checkbox input.
   *
   * @param checked whether the checkbox is ticked
   * @param label   optional visible label placed next to the checkbox
   */
  data class Checkbox(
    val checked: Boolean,
    val label: String? = null,
  ) : TablerTableCell
}

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
