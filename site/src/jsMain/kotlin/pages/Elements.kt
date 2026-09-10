@file:Layout("com.github.jangalinski.kobweb.tabler.site.SiteLayout")

package com.github.jangalinski.kobweb.tabler.site.pages

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerCards
import com.github.jangalinski.kobweb.tabler.site.SiteRoutes
import com.github.jangalinski.kobweb.tabler.site.siteLayoutData
import com.github.jangalinski.kobweb.tabler.site.sitePageMeta
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.HALF
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initElementsPage(ctx: InitRouteContext) {
  ctx.data.add(sitePageMeta("Elements", "Low-level Tabler elements"))
  ctx.data.add(siteLayoutData(SiteRoutes.Elements))
}

@Page(routeOverride = SiteRoutes.Elements)
@Composable
fun Elements() {
  TablerCards {
    card(title = "Icons", width = HALF) {
      P { Text("Icons can be used directly inside cards and navigation items.") }
    }
    card(title = "Tables", width = HALF) {
      P { Text("The table demonstrations are available under Elements > Tables.") }
    }
  }
}
