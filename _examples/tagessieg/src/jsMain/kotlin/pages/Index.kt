@file:Layout("net.janhoo.kotlin.kobweb.tabler.layouts.TablerLayout")

package net.janhoo.kotlin.kobweb.tabler.example.tagessieg.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.SiteRoutes
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.siteBreadcrumbs
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.siteLayoutData
import net.janhoo.kotlin.kobweb.tabler.example.tagessieg.sitePageMeta
import net.janhoo.kotlin.kobweb.tabler.components.TablerCards
import net.janhoo.kotlin.kobweb.tabler.styles.GridWidth.QUARTER
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
  }
}
