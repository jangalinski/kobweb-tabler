package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

/** Renders a data-driven Tabler table with responsive, no-wrap, and sticky-header options. */
@Composable
fun TablerTable(data: TablerTableData) {
  Div(attrs = {
    data.responsive.className?.let(::classes)
    prop({ element: HTMLDivElement, markup: String -> element.innerHTML = markup }, renderTableMarkup(data))
  })
}

private fun renderTableMarkup(data: TablerTableData): String = buildString {
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
      val tag = if (cell.isRowHeader) "th" else "td"
      append("<$tag")
      if (cell.isRowHeader) append(" scope=\"row\"")
      if (cell.muted) append(" class=\"${ClassNames.textSecondary}\"")
      append(">${cell.text.escapeHtml()}</$tag>")
    }
    append("</tr>")
  }
  append("</tbody></table>")
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
