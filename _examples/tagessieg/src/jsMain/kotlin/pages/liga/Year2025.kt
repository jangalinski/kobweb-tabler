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
 * Registers the Liga 2025 page metadata and shared layout data before the page renders.
 */
@InitRoute
fun initYear2025Page(ctx: InitRouteContext) {
  ctx.data.add(
    sitePageMeta(
      title = "Liga 2025",
      subtitle = "Season overview",
      breadcrumbs = siteBreadcrumbs(SiteRoutes.Liga2025),
    ),
  )
  ctx.data.add(siteLayoutData(activeRoute = SiteRoutes.Liga2025))
}

/**
 * Renders the Liga 2025 season page.
 */
@Page(routeOverride = SiteRoutes.Liga2025)
@Composable
fun Year2025() = seasonPage("2025")
