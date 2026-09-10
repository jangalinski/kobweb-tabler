@file:Layout("com.github.jangalinski.kobweb.tabler.site.SiteLayout")

package com.github.jangalinski.kobweb.tabler.site.pages

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerCards
import com.github.jangalinski.kobweb.tabler.elements.TablerIcon
import com.github.jangalinski.kobweb.tabler.elements.TablerIcon.TI_BRAND_GITHUB
import com.github.jangalinski.kobweb.tabler.elements.TablerIcon.TI_FOOTSTEPS
import com.github.jangalinski.kobweb.tabler.elements.invoke
import com.github.jangalinski.kobweb.tabler.site.SiteRoutes
import com.github.jangalinski.kobweb.tabler.site.siteLayoutData
import com.github.jangalinski.kobweb.tabler.site.sitePageMeta
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.HALF
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.QUARTER
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

/**
 * Registers the documentation home page metadata before the shared Tabler layout renders.
 */
@InitRoute
fun initIndexPage(ctx: InitRouteContext) {
  ctx.data.add(sitePageMeta("kobweb-tabler", "Documentation and examples"))
  ctx.data.add(siteLayoutData(SiteRoutes.Home))
}

/**
 * Renders the initial documentation home page.
 */
@Page
@Composable
fun Index() {
  TablerCards {
    statCard(
      title = "Components",
      value = "12",
      note = "Reusable UI building blocks",
      width = QUARTER,
    )
    statCard(
      title = "Elements",
      value = "24",
      note = "Low-level Tabler elements",
      width = QUARTER,
    )
    card(title = "Welcome", width = HALF) {
      P { Text("This site is the live component showcase for kobweb-tabler.") }
      P {
        A(href = "https://jangalinski.github.io/kobweb-tabler/docs/") {
          Text("Open the API documentation")
        }
      }
    }
    card(title = "Icons", width = HALF) {
      TI_BRAND_GITHUB(Modifier.fontSize(128.px).color(Colors.Pink))
      TI_FOOTSTEPS(Modifier.fontSize(128.px).color(Colors.Green))
    }
  }
}
