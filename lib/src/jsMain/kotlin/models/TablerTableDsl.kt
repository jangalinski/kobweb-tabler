package com.github.jangalinski.kobweb.tabler.models

import androidx.compose.runtime.Composable

/**
 * DSL marker that prevents unintended scope leakage in [TablerTableScope] lambdas.
 */
@DslMarker
annotation class TablerTableDsl

/**
 * Collected state for a DSL-built header row.
 *
 * Each entry in [cells] is a composable lambda that renders one header cell's content.
 */
@TablerTableDsl
class TablerTableHeaderScope internal constructor() {
  internal val cells = mutableListOf<@Composable () -> Unit>()

  /** Adds a header cell with arbitrary composable content. */
  fun cell(content: @Composable () -> Unit) {
    cells += content
  }
}

/**
 * Collected state for a single DSL-built data row.
 *
 * @property variant optional Tabler semantic row background applied to the `<tr>` element
 */
@TablerTableDsl
class TablerTableRowScope internal constructor(val variant: TablerTableRowVariant? = null) {
  internal val cells = mutableListOf<TablerTableDslCell>()

  /**
   * Adds a data cell with arbitrary composable content.
   *
   * @param isRowHeader when `true` the cell is rendered as a `<th scope="row">` instead of `<td>`
   */
  fun cell(isRowHeader: Boolean = false, content: @Composable () -> Unit) {
    cells += TablerTableDslCell(isRowHeader = isRowHeader, content = content)
  }
}

/** Internal model representing one composable-content cell in a DSL row. */
internal class TablerTableDslCell(
  val isRowHeader: Boolean,
  val content: @Composable () -> Unit,
)

/**
 * Top-level scope for building a [com.github.jangalinski.kobweb.tabler.components.TablerTable]
 * using the composable DSL.
 *
 * Example:
 * ```kotlin
 * TablerTable {
 *   header { cell { Text("Name") }; cell { Text("Status") } }
 *   row {
 *     cell { TablerAvatar(…) }
 *     cell { Text("Active") }
 *   }
 * }
 * ```
 */
@TablerTableDsl
class TablerTableScope internal constructor() {
  internal var header: TablerTableHeaderScope? = null
  internal val rows = mutableListOf<TablerTableRowScope>()

  /**
   * Defines the column header row.
   *
   * Call this at most once; subsequent calls overwrite the previous header.
   */
  fun header(block: TablerTableHeaderScope.() -> Unit) {
    header = TablerTableHeaderScope().apply(block)
  }

  /**
   * Adds a data row to the table.
   *
   * @param variant optional Tabler semantic background for the row
   */
  fun row(variant: TablerTableRowVariant? = null, block: TablerTableRowScope.() -> Unit) {
    rows += TablerTableRowScope(variant = variant).apply(block)
  }
}
