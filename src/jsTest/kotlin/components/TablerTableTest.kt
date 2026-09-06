package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarContent
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarData
import com.github.jangalinski.kobweb.tabler.models.TablerTableCell
import com.github.jangalinski.kobweb.tabler.models.TablerTableColumn
import com.github.jangalinski.kobweb.tabler.models.TablerTableData
import com.github.jangalinski.kobweb.tabler.models.TablerTableRow
import com.github.jangalinski.kobweb.tabler.models.TablerTableRowVariant
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test

@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerTableTest {

  private fun simpleData(vararg columnLabels: String, rows: List<List<String>> = emptyList()) =
    TablerTableData(
      columns = columnLabels.map { TablerTableColumn(it) },
      rows = rows.map { cells -> TablerTableRow(cells.map { TablerTableCell.Text(it) }) },
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
          cells = listOf(TablerTableCell.Text("OK")),
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

  @Test
  fun rendersMutedTextCell() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Note")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Text("quiet", muted = true)))),
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("text-secondary")
    assertThat(html).contains("quiet")
  }

  @Test
  fun rendersAvatarNameCell() = runTest {
    val cell = TablerTableCell.AvatarName(
      avatar = TablerAvatarData(content = TablerAvatarContent.Initials("JG")),
      name = "Jan Galinski",
    )
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Player")),
      rows = listOf(TablerTableRow(listOf(cell))),
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("avatar")
    assertThat(html).contains("Jan Galinski")
  }

  @Test
  fun rendersBadgeCell() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Status")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Badge("Active", variant = "bg-success")))),
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("badge")
    assertThat(html).contains("Active")
    assertThat(html).contains("bg-success")
  }

  @Test
  fun rendersBadgeCellWithoutVariant() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Status")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Badge("Pending")))),
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("badge")
    assertThat(html).contains("Pending")
  }

  @Test
  fun rendersTagsCell() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Tags")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Tags(listOf("kotlin", "compose", "tabler"))))),
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("kotlin")
    assertThat(html).contains("compose")
    assertThat(html).contains("tabler")
  }

  @Test
  fun rendersEmptyTagsCell() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Tags")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Tags(emptyList())))),
    )

    composition { TablerTable(data) }

    // should not throw; cell renders empty
    val html = root.innerHTML
    assertThat(html).contains("<td>")
  }

  @Test
  fun rendersCheckedCheckboxCell() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Select")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Checkbox(checked = true, label = "Pick me")))),
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("checkbox")
    assertThat(html).contains("checked")
    assertThat(html).contains("Pick me")
  }

  @Test
  fun rendersUncheckedCheckboxCell() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Select")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Checkbox(checked = false)))),
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("checkbox")
    assertThat(html).doesNotContain("checked")
  }

  @Test
  fun rendersPlaceholderRowsForExpectedDisplayRows() = runTest {
    val data = TablerTableData(
      columns = listOf(TablerTableColumn("Name")),
      rows = listOf(TablerTableRow(listOf(TablerTableCell.Text("Alice")))),
      expectedDisplayRows = 3,
    )

    composition { TablerTable(data) }

    val html = root.innerHTML
    assertThat(html).contains("Alice")
    assertThat(html.split("table-placeholder-row").size - 1).isEqualTo(2)
  }

  // --- DSL overload tests ---

  @Test
  fun dslRendersHeaderCells() = runTest {
    composition {
      TablerTable {
        header {
          cell { Text("Name") }
          cell { Text("Status") }
        }
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("Name")
    assertThat(html).contains("Status")
    assertThat(html).contains("<th")
  }

  @Test
  fun dslRendersRowCells() = runTest {
    composition {
      TablerTable {
        header { cell { Text("Player") } }
        row { cell { Text("Alice") } }
        row { cell { Text("Bob") } }
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("Alice")
    assertThat(html).contains("Bob")
  }

  @Test
  fun dslRendersAvatarInsideCell() = runTest {
    composition {
      TablerTable {
        header { cell { Text("Profile") } }
        row {
          cell {
            TablerAvatar(TablerAvatarData(content = TablerAvatarContent.Initials("JG")))
          }
        }
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("avatar")
    assertThat(html).contains("JG")
  }

  @Test
  fun dslRendersRowVariant() = runTest {
    composition {
      TablerTable {
        header { cell { Text("Result") } }
        row(variant = TablerTableRowVariant.SUCCESS) { cell { Text("Won") } }
      }
    }

    val html = root.innerHTML
    assertThat(html).contains("table-success")
  }
}
