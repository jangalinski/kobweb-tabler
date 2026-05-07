@file:Layout("net.janhoo.kotlin.kobweb.tabler.layouts.TablerLayout")

package net.janhoo.kotlin.kobweb.tabler.example.tagessieg.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.siteBreadcrumbs
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.siteLayoutData
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.sitePageMeta
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.SiteRoutes
import net.janhoo.kotlin.kobweb.tabler.charts.ApexDonutChart
import net.janhoo.kotlin.kobweb.tabler.charts.DonutSlice
import net.janhoo.kotlin.kobweb.tabler.components.TablerCard
import net.janhoo.kotlin.kobweb.tabler.components.TablerCards
import net.janhoo.kotlin.kobweb.tabler.styles.GridWidth.QUARTER
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

/**
 * Registers the analysis page metadata and shared layout data before the page renders.
 */
@InitRoute
fun initAnalysisPage(ctx: InitRouteContext) {
  ctx.data.add(
    sitePageMeta(
      title = "Analysis",
      subtitle = "Detailed breakdown",
      breadcrumbs = siteBreadcrumbs(SiteRoutes.Analyse),
    ),
  )
  ctx.data.add(siteLayoutData(activeRoute = SiteRoutes.Analyse))
}

/**
 * Renders the dedicated analysis page.
 */
@Page(routeOverride = SiteRoutes.Analyse)
@Composable
fun AnalysisPage() {
  TablerCards {
    statCard(
      title = "Seasons",
      value = "4",
      note = "Liga has yearly sub-pages.",
      width = QUARTER,
    )
    statCard(
      title = "Sections",
      value = "2",
      note = "Home and Analysis share one navigation.",
      width = QUARTER,
    )
    card(title = "Analysis", width = QUARTER) {
      P {
        Text("This is the dedicated analysis page.")
      }
      P {
        Text("It lives at /analysis.")
      }
    }

    card(title = "Dummy Chart", width = QUARTER) {
      ApexDonutChart(
        slices = listOf(
          DonutSlice(label = "Jens", value = 18),
          DonutSlice(label = "Holger", value = 12),
          DonutSlice(label = "Draw", value = 9),
        ),
      )
    }
  }
}
