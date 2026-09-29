package com.github.jangalinski.tabweb.site.pages

import androidx.compose.runtime.Composable
import com.github.jangalinski.tabweb.card.TablerCards
import com.github.jangalinski.tabweb.site.SiteRoutes
import com.github.jangalinski.tabweb.site.siteLayoutData
import com.github.jangalinski.tabweb.site.sitePageMeta
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initTablesPage(ctx: InitRouteContext) {
  ctx.data.add(sitePageMeta("Tables", "Tabler table demonstrations"))
  ctx.data.add(siteLayoutData(SiteRoutes.Tables))
}

@Page(routeOverride = SiteRoutes.Tables)
@Composable
fun Tables() {
  TablerCards {
    card(title = "Tables") {
      P { Text("A table component showcase will live here.") }
    }
  }
}
