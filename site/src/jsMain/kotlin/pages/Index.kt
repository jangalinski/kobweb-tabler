package com.github.jangalinski.kobweb.tabler.site.pages

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerCards
import com.github.jangalinski.kobweb.tabler._compose.KDiv
import com.github.jangalinski.kobweb.tabler.elements.TablerDivider
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon.TI_BRAND_GITHUB
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon.TI_FOOTSTEPS
import com.github.jangalinski.kobweb.tabler.elements.TablerLink
import com.github.jangalinski.kobweb.tabler.icon.invoke
import com.github.jangalinski.kobweb.tabler.site.SiteRoutes
import com.github.jangalinski.kobweb.tabler.site.siteLayoutData
import com.github.jangalinski.kobweb.tabler.site.sitePageMeta
import com.github.jangalinski.kobweb.tabler.styles.GridWidth
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.HALF
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.QUARTER
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H3
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
        colorCard("Blue")
        colorCard("Azure")
        colorCard("Indigo")
        colorCard("Purple")
        colorCard("Pink")
        colorCard("Red")
        colorCard("Orange")
        colorCard("Yellow")
        colorCard("Lime")
        colorCard("Green")
        colorCard("Teal")
        colorCard("Cyan")
      }

      TablerDivider()

      H3 { Text("Light colors") }
      Text("Every base color also has a light shade with the -lt suffix. It works as a background for text or an icon in the base color.")

      KDiv(modifier = Modifier.classNames("row", "row-cols-4", "row-cols-md-6", "g-3", "g-md-4")) {
        colorCard("Blue Light", "bg-blue-lt")
        colorCard("Azure Light", "bg-azure-lt")
        colorCard("Indigo Light", "bg-indigo-lt")
        colorCard("Purple Light", "bg-purple-lt")
        colorCard("Pink Light", "bg-pink-lt")
        colorCard("Red Light", "bg-red-lt")
        colorCard("Orange Light", "bg-orange-lt")
        colorCard("Yellow Light", "bg-yellow-lt")
        colorCard("Lime Light", "bg-lime-lt")
        colorCard("Green Light", "bg-green-lt")
        colorCard("Teal Light", "bg-teal-lt")
        colorCard("Cyan Light", "bg-cyan-lt")
      }

      TablerDivider()

      H3 { Text("Gray palette") }
      Text("The gray scale is used for backgrounds, borders and muted text. Tabler ships several gray palettes and switches between them with data-bs-theme-base.")

      KDiv(modifier = Modifier.classNames("row", "row-cols-4", "row-cols-md-6", "g-3", "g-md-4")) {
        colorCard("Gray 50", "bg-gray-50")
        colorCard("Gray 100", "bg-gray-100")
        colorCard("Gray 200", "bg-gray-200")
        colorCard("Gray 300", "bg-gray-300")
        colorCard("Gray 400", "bg-gray-400")
        colorCard("Gray 500", "bg-gray-500")
        colorCard("Gray 600", "bg-gray-600")
        colorCard("Gray 700", "bg-gray-700")
        colorCard("Gray 800", "bg-gray-800")
        colorCard("Gray 900", "bg-gray-900")
        colorCard("Gray 950", "bg-gray-950")
      }

      TablerDivider()

      H3 { Text("Social colors") }
      Text("The brand colors of popular services are available too, for social buttons and icons.")

      KDiv(modifier = Modifier.classNames("row", "row-cols-4", "row-cols-md-6", "g-3", "g-md-4")) {
        colorCard("Facebook", "bg-facebook")
        colorCard("Twitter", "bg-twitter")
        colorCard("X", "bg-x")
        colorCard("Linkedin", "bg-linkedin")
        colorCard("Google", "bg-google")
        colorCard("Youtube", "bg-youtube")
        colorCard("Vimeo", "bg-vimeo")
        colorCard("Dribbble", "bg-dribbble")
        colorCard("Github", "bg-github")
        colorCard("Instagram", "bg-instagram")
        colorCard("Pinterest", "bg-pinterest")
        colorCard("VK", "bg-vk")
        colorCard("RSS", "bg-rss")
        colorCard("Flickr", "bg-flickr")
        colorCard("Bitbucket", "bg-bitbucket")
        colorCard("Tabler", "bg-tabler")
      }
    }
  }
}
