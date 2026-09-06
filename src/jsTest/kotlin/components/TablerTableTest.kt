package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import com.github.jangalinski.kobweb.tabler.models.TablerTableCell
import com.github.jangalinski.kobweb.tabler.models.TablerTableColumn
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.models.TablerTableRow
import com.github.jangalinski.kobweb.tabler.models.TablerTableRowVariant
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test

@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerTableTest {

  private fun simpleData(vararg columnLabels: String, rows: List<List<String>> = emptyList()) =
    TablerTableData(
      columns = columnLabels.map { TablerTableColumn(it) },
      rows = rows.map { cells -> TablerTableRow(cells.map { TablerTableCell(it) }) },
    )

  @Test
  fun rendersColumnHeaders() = runTest {
    composition {
      TablerTable(simpleData("Name", "Email"))
    }

    val html = root.innerHTML
    assertThat(html).contains("<th")
    assertThat(html).contains("Name")
    assertThat(html).contains("Email")
  }

  @Test
  fun rendersTableRows() = runTest {
    composition {
      TablerTable(
        simpleData(
          "Name", "Role",
          rows = listOf(listOf("Alice", "Admin"), listOf("Bob", "User")),
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("Alice")
    assertThat(html).contains("Admin")
    assertThat(html).contains("Bob")
    assertThat(html).contains("User")
  }

  @Test
  fun usesTablerTableClass() = runTest {
    composition {
      TablerTable(simpleData("Col"))
    }

    val html = root.innerHTML
    assertThat(html).contains("table")
    assertThat(html).contains("card-table")
  }

  @Test
  fun rendersRowVariantClass() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Status")),
      rows = listOf(
        TablerTableRow(
          cells = listOf(TablerTableCell("OK")),
          variant = TablerTableRowVariant.SUCCESS,
        ),
      ),
    )

    composition {
      TablerTable(data)
    }

    val html = root.innerHTML
    assertThat(html).contains("table-success")
  }

  @Test
  fun escapesHtmlInCellText() = runTest {
    composition {
      TablerTable(simpleData("Col", rows = listOf(listOf("<script>alert(1)</script>"))))
    }

    val html = root.innerHTML
    assertThat(html).contains("&lt;script&gt;")
  }

  @Test
  fun rendersStickyHeaderClass() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("A")),
      rows = emptyList(),
      stickyHeader = true,
    )

    composition {
      TablerTable(data)
    }

    val html = root.innerHTML
    assertThat(html).contains("sticky-top")
  }
}
