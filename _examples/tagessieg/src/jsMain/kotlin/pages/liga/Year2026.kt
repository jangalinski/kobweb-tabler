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

/**
 * Registers the Liga 2026 page metadata and shared layout data before the page renders.
 */
@InitRoute
fun initYear2026Page(ctx: InitRouteContext) {
  ctx.data.add(
    sitePageMeta(
      title = "Liga 2026",
      subtitle = "Season overview",
      breadcrumbs = siteBreadcrumbs(SiteRoutes.Liga2026),
    ),
  )
  ctx.data.add(siteLayoutData(activeRoute = SiteRoutes.Liga2026))
}

/**
 * Renders the Liga 2026 season page.
 */
@Page(routeOverride = SiteRoutes.Liga2026)
@Composable
fun Year2026() = seasonPage("2026")
