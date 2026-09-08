package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.KobwebTabler.publicResourcePath
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarContent
import com.github.jangalinski.kobweb.tabler.models.TablerTableCell
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.models.TablerTableResponsive
import com.github.jangalinski.kobweb.tabler.models.TablerTableScope
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Table
import org.jetbrains.compose.web.dom.Tbody
import org.jetbrains.compose.web.dom.Td
import org.jetbrains.compose.web.dom.Th
import org.jetbrains.compose.web.dom.Thead
import org.jetbrains.compose.web.dom.Tr
import org.w3c.dom.HTMLDivElement

/** Renders a data-driven Tabler table with responsive, no-wrap, and sticky-header options. */
@Composable
fun TablerTable(data: TablerTableData) {
  Div(attrs = {
    data.responsive.className?.let(::classes)
    prop({ element: HTMLDivElement, markup: String -> element.innerHTML = markup }, renderTableMarkup(data))
  })
}

/**
 * Composable DSL overload of [TablerTable] that accepts arbitrary composable cell content.
 *
 * Use this overload when cells need to embed existing components such as [TablerAvatar],
 * badges, or links.  For plain-text tables the [TablerTable] data overload is simpler.
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
 *
 * @param responsive breakpoint at which horizontal scrolling starts
 * @param noWrap     prevents text wrapping in all cells when `true`
 * @param stickyHeader makes the header row stick to the viewport top when scrolling
 * @param block      DSL builder that declares the header and rows
 */
@Composable
fun TablerTable(
  responsive: TablerTableResponsive = TablerTableResponsive.ALWAYS,
  noWrap: Boolean = false,
  stickyHeader: Boolean = false,
  block: TablerTableScope.() -> Unit,
) {
  val scope = TablerTableScope().apply(block)
  Div(attrs = { responsive.className?.let(::classes) }) {
    Table(attrs = {
      classes(*ClassNames.table.trim().split(Regex("\\s+")).toTypedArray())
      if (noWrap) classes(ClassNames.tableNoWrap)
    }) {
      scope.header?.let { headerScope ->
        Thead(attrs = {
          if (stickyHeader) classes(ClassNames.stickyTop)
        }) {
          Tr {
            headerScope.cells.forEach { cellContent ->
              Th(attrs = { attr("scope", "col") }) { cellContent() }
            }
          }
        }
      }
      Tbody {
        scope.rows.forEach { rowScope ->
          Tr(attrs = {
            rowScope.variant?.let { classes(it.className) }
          }) {
            rowScope.cells.forEach { cell ->
              if (cell.isRowHeader) {
                Th(attrs = { attr("scope", "row") }) { cell.content() }
              } else {
                Td { cell.content() }
              }
            }
          }
        }
      }
    }
  }
}

internal fun renderTableMarkup(data: TablerTableData): String = buildString {
  append("<table class=\"")
  append(ClassNames.table)
  if (data.noWrap) append(" ${ClassNames.tableNoWrap}")
  append("\"><thead")
  if (data.stickyHeader) append(" class=\"${ClassNames.stickyTop}\"")
  append("><tr>")
  data.columns.forEach { column ->
    append("<th scope=\"col\"")
    if (column.noWrap) append(" class=\"${ClassNames.textNoWrap}\"")
    append(">${column.label.escapeHtml()}</th>")
  }
  append("</tr></thead><tbody>")
  data.rows.forEach { row ->
    append("<tr")
    row.variant?.let { append(" class=\"${it.className}\"") }
    append(">")
    row.cells.forEach { cell ->
      append(renderCellMarkup(cell))
    }
    append("</tr>")
  }
  val placeholderRows = ((data.expectedDisplayRows ?: 0) - data.rows.size).coerceAtLeast(0)
  repeat(placeholderRows) {
    append("<tr class=\"${ClassNames.tablePlaceholderRow}\" aria-hidden=\"true\">")
    repeat(data.columns.size) {
      append("<td>&nbsp;</td>")
    }
    append("</tr>")
  }
  append("</tbody></table>")
}

private fun renderCellMarkup(cell: TablerTableCell): String = buildString {
  val tag = if (cell.isRowHeader) "th" else "td"
  append("<$tag")
  if (cell.isRowHeader) append(" scope=\"row\"")
  when (cell) {
    is TablerTableCell.Text -> {
      if (cell.muted) append(" class=\"${ClassNames.textSecondary}\"")
      append(">${cell.value.escapeHtml()}</$tag>")
    }
    is TablerTableCell.AvatarName -> {
      append(">")
      append(renderAvatarNameMarkup(cell))
      append("</$tag>")
    }
    is TablerTableCell.Badge -> {
      append(">")
      append("<span class=\"badge")
      cell.variant?.let { append(" ${it.escapeHtml()}") }
      append("\">${cell.label.escapeHtml()}</span>")
      append("</$tag>")
    }
    is TablerTableCell.Tags -> {
      append(">")
      cell.tags.forEach { tag2 ->
        append("<span class=\"badge bg-azure-lt me-1\">${tag2.escapeHtml()}</span>")
      }
      append("</$tag>")
    }
    is TablerTableCell.Checkbox -> {
      append(">")
      append("<label class=\"form-check\">")
      append("<input class=\"form-check-input\" type=\"checkbox\"")
      if (cell.checked) append(" checked")
      append(">")
      cell.label?.let { append("<span class=\"form-check-label\">${it.escapeHtml()}</span>") }
      append("</label>")
      append("</$tag>")
    }
  }
}

private fun renderAvatarNameMarkup(cell: TablerTableCell.AvatarName): String = buildString {
  val avatar = cell.avatar
  val cssClasses = buildList {
    add(ClassNames.avatar)
    avatar.color?.let { add(it.backgroundClassName); add(it.foregroundClassName) }
    avatar.size.className?.let { add(it) }
    avatar.shape.className?.let { add(it) }
  }.joinToString(" ")
  append("<span class=\"$cssClasses\"")
  avatar.ariaLabel?.let { append(" aria-label=\"${it.escapeHtml()}\"") }
  if (avatar.content is TablerAvatarContent.ImageResource) {
    append(" style=\"background-color: var(--tblr-bg-surface)\"")
  }
  append(">")
  when (val content = avatar.content) {
    is TablerAvatarContent.Initials -> append(content.value.escapeHtml())
    is TablerAvatarContent.ImageResource -> {
      append("<img src=\"${publicResourcePath(content.resource).escapeHtml()}\"")
      append(" alt=\"${content.altText.orEmpty().escapeHtml()}\"")
      append(" style=\"display: block; width: 100%; height: 100%; object-fit: cover; border-radius: inherit\">")
    }
    is TablerAvatarContent.Icon -> {
      append("<img src=\"${svgDataUri(content.svg).escapeHtml()}\"")
      append(" alt=\"${content.altText.orEmpty().escapeHtml()}\"")
      append(" class=\"${ClassNames.icon}\">")
    }
  }
  append("</span>")
  append("<span class=\"ms-2\">${cell.name.escapeHtml()}</span>")
}

private fun String.escapeHtml(): String = buildString {
  this@escapeHtml.forEach { char ->
    append(
      when (char) {
        '&' -> "&amp;"
        '<' -> "&lt;"
        '>' -> "&gt;"
        '"' -> "&quot;"
        '\'' -> "&#39;"
        else -> char
      },
    )
  }
}
