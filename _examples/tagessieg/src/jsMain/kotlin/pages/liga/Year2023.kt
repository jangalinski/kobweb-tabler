@file:Layout("com.github.jangalinski.kobweb.tabler.layouts.TablerLayout")

package com.github.jangalinski.kobweb.tabler.example.tagessieg.pages.liga

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

/**
 * Registers the Liga 2023 page metadata and shared layout data before the page renders.
 */
@InitRoute
fun initYear2023Page(ctx: InitRouteContext) {
  ctx.data.add(
    sitePageMeta(
      title = "Liga 2023",
      subtitle = "Season overview",
      breadcrumbs = siteBreadcrumbs(SiteRoutes.Liga2023),
    ),
  )
  ctx.data.add(siteLayoutData(activeRoute = SiteRoutes.Liga2023))
}

/**
 * Renders the Liga 2023 season page.
 */
@Page(routeOverride = SiteRoutes.Liga2023)
@Composable
fun Year2023() = seasonPage("2023")
