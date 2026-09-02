@file:Layout("com.github.jangalinski.kobweb.tabler.layouts.TablerLayout")

package com.github.jangalinski.kobweb.tabler.example.tagessieg.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.github.jangalinski.kobweb.tabler.example.tagessieg.SiteRoutes
import com.github.jangalinski.kobweb.tabler.example.tagessieg.siteBreadcrumbs
import com.github.jangalinski.kobweb.tabler.example.tagessieg.siteLayoutData
import com.github.jangalinski.kobweb.tabler.example.tagessieg.sitePageMeta
import com.github.jangalinski.kobweb.tabler.components.TablerCards
import com.github.jangalinski.kobweb.tabler.components.TablerTracking
import com.github.jangalinski.kobweb.tabler.components.TrackingBlock
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler.styles.GridWidth
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.HALF
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.QUARTER
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
  }
}
