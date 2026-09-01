@file:Layout("io.github.jangalinski.kotlin.kobweb.tabler.layouts.TablerLayout")

package io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg.SiteRoutes
import io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg.siteBreadcrumbs
import io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg.siteLayoutData
import io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg.sitePageMeta
import io.github.jangalinski.kotlin.kobweb.tabler.components.TablerCards
import io.github.jangalinski.kotlin.kobweb.tabler.components.TablerTracking
import io.github.jangalinski.kotlin.kobweb.tabler.components.TrackingBlock
import io.github.jangalinski.kotlin.kobweb.tabler.styles.ClassNames
import io.github.jangalinski.kotlin.kobweb.tabler.styles.ClassNames.modifier
import io.github.jangalinski.kotlin.kobweb.tabler.styles.GridWidth
import io.github.jangalinski.kotlin.kobweb.tabler.styles.GridWidth.HALF
import io.github.jangalinski.kotlin.kobweb.tabler.styles.GridWidth.QUARTER
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
