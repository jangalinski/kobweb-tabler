@file:Layout("com.github.jangalinski.kobweb.tabler.layouts.TablerLayout")

package com.github.jangalinski.kobweb.tabler.site.pages

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerCard
import com.github.jangalinski.kobweb.tabler.elements.TablerIcon
import com.github.jangalinski.kobweb.tabler.elements.TablerIcon.TI_BRAND_GITHUB
import com.github.jangalinski.kobweb.tabler.elements.TablerIcon.TI_FOOTSTEPS
import com.github.jangalinski.kobweb.tabler.elements.invoke
import com.github.jangalinski.kobweb.tabler.models.TablerLayoutData
import com.github.jangalinski.kobweb.tabler.models.TablerPageMeta
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
  ctx.data.add(TablerPageMeta(title = "kobweb-tabler", subtitle = "Documentation"))
  ctx.data.add(TablerLayoutData())
}

/**
 * Renders the initial documentation home page.
 */
@Page
@Composable
fun Index() {
  TablerCard(title = "Hello world") {
    P {
      Text("kobweb-tabler documentation site")
    }
    P {
      A(href = "https://jangalinski.github.io/kobweb-tabler/docs/") {
        Text("Open the API documentation")
      }
    }
  }

  TablerCard {
    TI_BRAND_GITHUB(Modifier.fontSize(128.px).color(Colors.Pink))

    TI_FOOTSTEPS(Modifier.fontSize(128.px).color(Colors.Green))
  }


  TablerCard {
    TablerIcon.entries.forEach {
      it()
    }
  }
}
