package com.github.jangalinski.kobweb.tabler.site.pages

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.card.TablerCards
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.compose.KSpan
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.github.jangalinski.kobweb.tabler.divider.TablerDivider
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon.TI_BRAND_GITHUB
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon.TI_FOOTSTEPS
import com.github.jangalinski.kobweb.tabler.link.TablerLink
import com.github.jangalinski.kobweb.tabler.site.SiteRoutes
import com.github.jangalinski.kobweb.tabler.site.siteLayoutData
import com.github.jangalinski.kobweb.tabler.site.sitePageMeta
import com.github.jangalinski.kobweb.tabler._foundation.css.GridWidth
import com.github.jangalinski.kobweb.tabler._foundation.css.GridWidth.HALF
import com.github.jangalinski.kobweb.tabler._foundation.css.GridWidth.QUARTER
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon
import com.varabyte.kobweb.compose.style.KobwebComposeStyleSheet.attr
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
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
        A(href = "https://jangalinski.github.io/kobweb-tabler/docs/", attrs = { attr("target", "_blank") }) {
          Text("Open the API documentation")
        }
      }
      P {
        TablerLink(href = "https://jangalinski.github.io/kobweb-tabler/docs/")
      }
      P {
        TablerLink(href = SiteRoutes.Elements)
      }
    }

    card(title = "Icons", width = HALF) {

      KSpan(Modifier.classNames("avatar").then(Modifier.styleModifier {
        backgroundImage("url(/avatars/jan-g-avatar.png)")
      }))

      KSpan(Modifier.classNames("avatar")) {
        TablerIcon.TI_HOME()
      }

      KSpan(Modifier.classNames("avatar")) {
        KText("JGX")
      }

      Img(src = "/avatars/jan-g-avatar.png", attrs = {
        attr("width", "256")
        attr("height", "256")
        attr("alt", "Jan G Avatar")
      })

      TI_BRAND_GITHUB(Modifier.fontSize(128.px).size(128.px).color(Colors.Pink))
      TI_FOOTSTEPS(Modifier.fontSize(128.px).size(128.px).color(Colors.Green))
    }

    @Composable
    fun colorCard(colorName: String, colorClass: String? = null) {
      KDiv(modifier = Modifier.classNames("text-center")) {
        KDiv(modifier = Modifier.classNames("p-6", "rounded", "border", colorClass ?: "bg-${colorName.lowercase()}")){}
        KDiv(modifier = Modifier.classNames("small")) { Text(colorName) }
      }
    }

    card(title = "Colors", width = GridWidth.FULL) {
      Text("The Tabler color palette with base colors, light variants, the gray scale and social brand colors, each with background and text utilities.")

      TablerDivider(text = "Color palette")

      H3 { Text("Base colors") }
      Text("These are the base colors. Each one has bg-* and text-* utilities, and the components use the same names for their color variants.")

      KDiv(modifier = Modifier.classNames("row", "row-cols-4", "row-cols-md-6", "g-3", "g-md-4")) {
        BackgroundColor.BASE.entries.forEach { color ->
          colorCard(color.displayName, color.value)
        }
      }

      TablerDivider()

      H3 { Text("Light colors") }
      Text("Every base color also has a light shade with the -lt suffix. It works as a background for text or an icon in the base color.")

      KDiv(modifier = Modifier.classNames("row", "row-cols-4", "row-cols-md-6", "g-3", "g-md-4")) {
        BackgroundColor.LIGHT.entries.forEach { color ->
          colorCard(color.displayName, color.value)
        }
      }

      TablerDivider()

      H3 { Text("Gray palette") }
      Text("The gray scale is used for backgrounds, borders and muted text. Tabler ships several gray palettes and switches between them with data-bs-theme-base.")

      KDiv(modifier = Modifier.classNames("row", "row-cols-4", "row-cols-md-6", "g-3", "g-md-4")) {
        BackgroundColor.GRAY.entries.forEach { color ->
          colorCard(color.displayName, color.value)
        }
      }

      TablerDivider()

      H3 { Text("Social colors") }
      Text("The brand colors of popular services are available too, for social buttons and icons.")

      KDiv(modifier = Modifier.classNames("row", "row-cols-4", "row-cols-md-6", "g-3", "g-md-4")) {
        BackgroundColor.SOCIAL.entries.forEach { color ->
          colorCard(color.displayName, color.value)
        }
      }
    }
  }
}
