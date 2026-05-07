@file:Layout("net.janhoo.kotlin.kobweb.tabler.layouts.TablerLayout")

package net.janhoo.kotlin.kobweb.tabler.example.tagessieg.pages.liga

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
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

/**
 * Registers the Liga overview page metadata and shared layout data before the page renders.
 */
@InitRoute
fun initLigaIndexPage(ctx: InitRouteContext) {
  ctx.data.add(
    sitePageMeta(
      title = "Liga",
      subtitle = "League overview",
      breadcrumbs = siteBreadcrumbs(SiteRoutes.Liga),
    ),
  )
  ctx.data.add(siteLayoutData(activeRoute = SiteRoutes.Liga))
}

/**
 * Renders the Liga overview page.
 */
@Page
@Composable
fun Index() {
  TablerCards {
    card(title = "Liga") {
      P {
        Text("This is the league overview page.")
      }
      P {
        Text("Use the dropdown in the shared nav to open the season pages.")
      }
    }
  }
}



/**
 * Renders a simple Liga season card for the given year.
 *
 * The individual year pages call this helper so the body content stays tiny.
 */
@Composable
fun seasonPage(year: String) {
  TablerCards {
    card(title = "Liga $year") {
      P {
        Text("Dummy season page for $year.")
      }
    }
  }
}
