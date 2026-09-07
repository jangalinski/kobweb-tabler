@file:Layout("com.github.jangalinski.kobweb.tabler.layouts.TablerLayout")

package com.github.jangalinski.kobweb.tabler.example.tagessieg.pages

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.*
import com.github.jangalinski.kobweb.tabler.example.tagessieg.SiteRoutes
import com.github.jangalinski.kobweb.tabler.example.tagessieg.siteBreadcrumbs
import com.github.jangalinski.kobweb.tabler.example.tagessieg.siteLayoutData
import com.github.jangalinski.kobweb.tabler.example.tagessieg.sitePageMeta
import com.github.jangalinski.kobweb.tabler.models.*
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarContent.ImageResource
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler.styles.GridWidth
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.HALF
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.QUARTER
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

/**
 * Registers the home page metadata and shared layout data before the page renders.
 */
@InitRoute
fun initIndexPage(ctx: InitRouteContext) {
  ctx.data.add(sitePageMeta(title = "Home", subtitle = "Tagessieg", breadcrumbs = siteBreadcrumbs(SiteRoutes.Root)))
  ctx.data.add(siteLayoutData(activeRoute = SiteRoutes.Root))
}

/**
 * Renders the home page content.
 */
@Page
@Composable
fun Index() {
  val recentMatchRows = rememberPaginatedTableRows(
    rows = listOf(
      TablerTableRow(
        listOf(
          TablerTableCell.Text("01 Sep 2026", isRowHeader = true),
          TablerTableCell.Text("FC Beispiel"),
          TablerTableCell.Text("3 – 1"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("27 Aug 2026", isRowHeader = true),
          TablerTableCell.Text("SV Vorlage"),
          TablerTableCell.Text("2 – 2"),
          TablerTableCell.Text("Draw", muted = true),
        ),
        variant = TablerTableRowVariant.WARNING,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("21 Aug 2026", isRowHeader = true),
          TablerTableCell.Text("TSV Daten"),
          TablerTableCell.Text("0 – 1"),
          TablerTableCell.Text("Lost", muted = true),
        ),
        variant = TablerTableRowVariant.DANGER,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("14 Aug 2026", isRowHeader = true),
          TablerTableCell.Text("SC Sample"),
          TablerTableCell.Text("4 – 2"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("08 Aug 2026", isRowHeader = true),
          TablerTableCell.Text("VfL Demo"),
          TablerTableCell.Text("1 – 1"),
          TablerTableCell.Text("Draw", muted = true),
        ),
        variant = TablerTableRowVariant.WARNING,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("02 Aug 2026", isRowHeader = true),
          TablerTableCell.Text("FC Fixture"),
          TablerTableCell.Text("2 – 0"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("29 Jul 2026", isRowHeader = true),
          TablerTableCell.Text("TSG Mock"),
          TablerTableCell.Text("1 – 3"),
          TablerTableCell.Text("Lost", muted = true),
        ),
        variant = TablerTableRowVariant.DANGER,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("22 Jul 2026", isRowHeader = true),
          TablerTableCell.Text("DJK Musterstadt"),
          TablerTableCell.Text("2 – 1"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("18 Jul 2026", isRowHeader = true),
          TablerTableCell.Text("FC Kontroll"),
          TablerTableCell.Text("0 – 0"),
          TablerTableCell.Text("Draw", muted = true),
        ),
        variant = TablerTableRowVariant.WARNING,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("11 Jul 2026", isRowHeader = true),
          TablerTableCell.Text("SV Tabellen"),
          TablerTableCell.Text("1 – 2"),
          TablerTableCell.Text("Lost", muted = true),
        ),
        variant = TablerTableRowVariant.DANGER,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("04 Jul 2026", isRowHeader = true),
          TablerTableCell.Text("TuS Ansicht"),
          TablerTableCell.Text("5 – 2"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("28 Jun 2026", isRowHeader = true),
          TablerTableCell.Text("VfB Seitenzahl"),
          TablerTableCell.Text("3 – 3"),
          TablerTableCell.Text("Draw", muted = true),
        ),
        variant = TablerTableRowVariant.WARNING,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("21 Jun 2026", isRowHeader = true),
          TablerTableCell.Text("SC Kontrolle"),
          TablerTableCell.Text("4 – 0"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("14 Jun 2026", isRowHeader = true),
          TablerTableCell.Text("TSV Beispiel II"),
          TablerTableCell.Text("2 – 4"),
          TablerTableCell.Text("Lost", muted = true),
        ),
        variant = TablerTableRowVariant.DANGER,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("07 Jun 2026", isRowHeader = true),
          TablerTableCell.Text("FC Pagination"),
          TablerTableCell.Text("1 – 0"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("31 May 2026", isRowHeader = true),
          TablerTableCell.Text("SV Fenster"),
          TablerTableCell.Text("2 – 2"),
          TablerTableCell.Text("Draw", muted = true),
        ),
        variant = TablerTableRowVariant.WARNING,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("24 May 2026", isRowHeader = true),
          TablerTableCell.Text("VfL Anzeige"),
          TablerTableCell.Text("0 – 2"),
          TablerTableCell.Text("Lost", muted = true),
        ),
        variant = TablerTableRowVariant.DANGER,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("17 May 2026", isRowHeader = true),
          TablerTableCell.Text("TSG Demo II"),
          TablerTableCell.Text("3 – 0"),
          TablerTableCell.Text("Won", muted = true),
        ),
        variant = TablerTableRowVariant.SUCCESS,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("10 May 2026", isRowHeader = true),
          TablerTableCell.Text("SC Static"),
          TablerTableCell.Text("1 – 1"),
          TablerTableCell.Text("Draw", muted = true),
        ),
        variant = TablerTableRowVariant.WARNING,
      ),
      TablerTableRow(
        listOf(
          TablerTableCell.Text("03 May 2026", isRowHeader = true),
          TablerTableCell.Text("DJK Export"),
          TablerTableCell.Text("2 – 3"),
          TablerTableCell.Text("Lost", muted = true),
        ),
        variant = TablerTableRowVariant.DANGER,
      ),
    ),
    pageSize = 3,
    texts = TablerPaginationTexts(summaryTemplate = "Showing {index} of {max} matches"),
    window = TablerPaginationWindow(maxVisiblePageNumbers = 5),
  )

  TablerCards {
    statCard(
      title = "Home",
      value = "Tagessieg",
      note = "Brand click target and landing page.",
      width = QUARTER,
    )
    card(title = "Welcome", width = QUARTER) {
      P {
        Text("This is the home page.")
      }
      P {
        Text("Use Analysis for the analysis page.")
      }
      TablerAvatar(
        TablerAvatarData(
          content = ImageResource("heiko-w-l.png", altText = "Heiko W."),
          size = TablerAvatarSize.EXTRA_LARGE,
          shape = TablerAvatarShape.LARGE_ROUNDED,
          ariaLabel = "HW",
        ),
      )
    }
    card(title = "Avatar profiles", width = HALF) {
      P(attrs = { attr("class", ClassNames.textSecondaryM0) }) {
        Text("Image, initials, and icon avatars with Tabler variants")
      }
      Div(attrs = { attr("class", "d-flex align-items-center gap-3 mt-3") }) {
        TablerAvatar(
          TablerAvatarData(
            content = ImageResource("jan-g-avatar.png", altText = "Jan Galinski"),
            size = TablerAvatarSize.LARGE,
            shape = TablerAvatarShape.CIRCLE,
            status = TablerAvatarStatus(TablerAvatarStatusColor.SUCCESS),
            ariaLabel = "Jan Galinski, online",
          ),
        )
        TablerAvatar(
          TablerAvatarData(
            content = TablerAvatarContent.Initials("JG"),
            color = TablerAvatarColor.PURPLE,
            shape = TablerAvatarShape.LARGE_ROUNDED,
            status = TablerAvatarStatus(TablerAvatarStatusColor.WARNING, label = "2"),
            ariaLabel = "Jan Galinski, two notifications",
          ),
        )
        TablerAvatar(
          TablerAvatarData(
            content = TablerAvatarContent.Icon(
              svg = USER_ICON_SVG,
              altText = "User profile",
            ),
            color = TablerAvatarColor.AZURE,
            size = TablerAvatarSize.SMALL,
            shape = TablerAvatarShape.SQUARE,
            status = TablerAvatarStatus(TablerAvatarStatusColor.INFO),
            ariaLabel = "User profile",
          ),
        )
        TablerAvatar(
          TablerAvatarData(
            content = ImageResource("jan-g-avatar.png", altText = "Jan Galinski"),
            size = TablerAvatarSize.EXTRA_LARGE,
            shape = TablerAvatarShape.LARGE_ROUNDED,
            ariaLabel = "Jan Galinski, online",
          ),
        )
      }
      val listAvatars = listOf(
        TablerAvatarData(
          content = ImageResource("jan-g-avatar.png", altText = "Jan Galinski"),
          size = TablerAvatarSize.SMALL,
          shape = TablerAvatarShape.CIRCLE,
          ariaLabel = "Jan Galinski",
        ),
        TablerAvatarData(
          content = TablerAvatarContent.Initials("JG"),
          color = TablerAvatarColor.PURPLE,
          size = TablerAvatarSize.SMALL,
          shape = TablerAvatarShape.CIRCLE,
          ariaLabel = "Jan Galinski",
        ),
        TablerAvatarData(
          content = TablerAvatarContent.Icon(svg = USER_ICON_SVG, altText = "User profile"),
          color = TablerAvatarColor.AZURE,
          size = TablerAvatarSize.SMALL,
          shape = TablerAvatarShape.CIRCLE,
          ariaLabel = "User profile",
        ),
      )
      P(attrs = { attr("class", "text-secondary mt-3 mb-1") }) {
        Text("Avatar list")
      }
      TablerAvatarList(TablerAvatarListData(avatars = listAvatars))
      P(attrs = { attr("class", "text-secondary mt-3 mb-1") }) {
        Text("Stacked avatar list")
      }
      TablerAvatarList(TablerAvatarListData(avatars = listAvatars, stacked = true))
    }
    card(title = "Status monitoring", width = GridWidth.THIRD) {
      P(attrs = { attr("class", ClassNames.textSecondaryM0) }) {
        Text("Recent Tagessieg data refreshes")
      }
      TablerTracking(
        blocks = listOf(
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "High source load", variantClass = "bg-warning"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Source unavailable", variantClass = "bg-danger"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "No refresh data"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "High source load", variantClass = "bg-warning"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
        ),
        modifier = ClassNames.mt2.modifier(),
      )
    }
    tableCard(
      title = "Recent matches",
      subtitle = "Client-side static pagination",
      width = HALF,
      columns = listOf(
        TablerTableColumn("Date", noWrap = true),
        TablerTableColumn("Opponent"),
        TablerTableColumn("Score", noWrap = true),
        TablerTableColumn("Status"),
      ),
      rows = recentMatchRows,
      responsive = TablerTableResponsive.SMALL,
      noWrap = true,
      stickyHeader = true,
    )
    // --- AvatarName + Badge + Tags cells ---
    tableCard(
      title = "Player roster",
      subtitle = "Active squad with roles and tags",
      width = GridWidth.FULL,
      data = TablerTableData(
        columns = listOf(
          TablerTableColumn("Player"),
          TablerTableColumn("Status"),
          TablerTableColumn("Tags"),
        ),
        rows = listOf(
          TablerTableRow(
            listOf(
              TablerTableCell.AvatarName(
                avatar = TablerAvatarData(
                  content = ImageResource("jan-g-avatar.png", altText = "Jan Galinski"),
                  size = TablerAvatarSize.SMALL,
                  shape = TablerAvatarShape.CIRCLE,
                  ariaLabel = "Jan Galinski",
                ),
                name = "Jan Galinski",
              ),
              TablerTableCell.Badge("Active", "bg-success"),
              TablerTableCell.Tags(listOf("striker", "captain")),
            ),
          ),
          TablerTableRow(
            listOf(
              TablerTableCell.AvatarName(
                avatar = TablerAvatarData(
                  content = TablerAvatarContent.Initials("HW"),
                  color = TablerAvatarColor.ORANGE,
                  size = TablerAvatarSize.SMALL,
                  shape = TablerAvatarShape.CIRCLE,
                  ariaLabel = "Heiko W.",
                ),
                name = "Heiko W.",
              ),
              TablerTableCell.Badge("Injured", "bg-danger"),
              TablerTableCell.Tags(listOf("midfielder", "left-foot")),
            ),
          ),
          TablerTableRow(
            listOf(
              TablerTableCell.AvatarName(
                avatar = TablerAvatarData(
                  content = TablerAvatarContent.Icon(svg = USER_ICON_SVG, altText = "Unknown"),
                  color = TablerAvatarColor.AZURE,
                  size = TablerAvatarSize.SMALL,
                  shape = TablerAvatarShape.CIRCLE,
                  ariaLabel = "Unknown player",
                ),
                name = "Unknown",
              ),
              TablerTableCell.Badge("Scouting", "badge-outline text-blue"),
              TablerTableCell.Tags(emptyList()),
            ),
          ),
        ),
        responsive = TablerTableResponsive.SMALL,
      ),
    )

    // --- Checkbox cells ---
    tableCard(
      title = "Pre-match checklist",
      subtitle = "Tasks before kick-off",
      width = HALF,
      data = TablerTableData(
        columns = listOf(
          TablerTableColumn("Done"),
          TablerTableColumn("Task"),
        ),
        rows = listOf(
          TablerTableRow(listOf(TablerTableCell.Checkbox(checked = true), TablerTableCell.Text("Warm-up completed"))),
          TablerTableRow(listOf(TablerTableCell.Checkbox(checked = true), TablerTableCell.Text("Tactics briefing done"))),
          TablerTableRow(listOf(TablerTableCell.Checkbox(checked = false), TablerTableCell.Text("Kit check pending"))),
          TablerTableRow(listOf(TablerTableCell.Checkbox(checked = false, label = "!"), TablerTableCell.Text("Referee notification"))),
        ),
      ),
    )

    // --- DSL builder table ---
    card(title = "Top scorers (DSL)", width = HALF) {
      TablerTable {
        header {
          cell { Text("Player") }
          cell { Text("Goals") }
          cell { Text("Assists") }
        }
        row {
          cell {
            TablerAvatar(
              TablerAvatarData(
                content = ImageResource("jan-g-avatar.png", altText = "Jan G."),
                size = TablerAvatarSize.SMALL,
                shape = TablerAvatarShape.CIRCLE,
                status = TablerAvatarStatus(TablerAvatarStatusColor.SUCCESS),
                ariaLabel = "Jan Galinski",
              ),
            )
          }
          cell { Text("12") }
          cell { Text("7") }
        }
        row {
          cell {
            TablerAvatar(
              TablerAvatarData(
                content = TablerAvatarContent.Initials("HW"),
                color = TablerAvatarColor.ORANGE,
                size = TablerAvatarSize.SMALL,
                shape = TablerAvatarShape.CIRCLE,
                ariaLabel = "Heiko W.",
              ),
            )
          }
          cell { Text("8") }
          cell { Text("5") }
        }
        row(variant = TablerTableRowVariant.SUCCESS) {
          cell {
            TablerAvatar(
              TablerAvatarData(
                content = TablerAvatarContent.Icon(svg = USER_ICON_SVG, altText = "Scout pick"),
                color = TablerAvatarColor.AZURE,
                size = TablerAvatarSize.SMALL,
                shape = TablerAvatarShape.CIRCLE,
                ariaLabel = "Scout pick",
              ),
            )
          }
          cell { Text("5") }
          cell { Text("9") }
        }
      }
    }
  }

}

private const val USER_ICON_SVG = """
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
    <path d="M8 7a4 4 0 1 0 8 0a4 4 0 0 0 -8 0" />
    <path d="M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
  </svg>
"""
