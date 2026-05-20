package net.janhoo.kotlin.kobweb.tabler.example.tagessieg

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.navigation.BasePath
import net.janhoo.kotlin.kobweb.tabler.components.TablerNavigation.HeaderNavigation
import net.janhoo.kotlin.kobweb.tabler.models.navigationItems
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

/**
 * Builds the shared top navigation for the site.
 *
 * The navigation is intentionally site-local for now, but it already flows through the
 * shared `TablerLayoutData` seam so a generated catalog can replace it later.
 */
fun sharedNavigation(activeRoute: String): HeaderNavigation {
  return HeaderNavigation(
    href = BasePath.prependTo(SiteRoutes.Root),
    logo = SiteConfig.LOGO,
    items = navigationItems {
      link(
        name = "Analyse",
        href = BasePath.prependTo(SiteRoutes.Analyse),
        icon = SiteIcons.Analyse,
        active = activeRoute == SiteRoutes.Analyse,
      )
      dropdown(
        name = "Liga",
        href = BasePath.prependTo(SiteRoutes.Liga),
        icon = SiteIcons.Liga,
        active = activeRoute.startsWith(SiteRoutes.Liga),
        items = {
          link("2026", BasePath.prependTo(SiteRoutes.Liga2026), active = activeRoute == SiteRoutes.Liga2026)
          link("2025", BasePath.prependTo(SiteRoutes.Liga2025), active = activeRoute == SiteRoutes.Liga2025)
          link("2024", BasePath.prependTo(SiteRoutes.Liga2024), active = activeRoute == SiteRoutes.Liga2024)
          link("2023", BasePath.prependTo(SiteRoutes.Liga2023), active = activeRoute == SiteRoutes.Liga2023)
        },
      )
    },
    content = {
      GeneratedAt()
    },
  )
}

/**
 * Renders the timestamp block on the right side of the navbar.
 *
 * This is a simple example of layout content that is neither page body nor brand nav.
 */
@Composable
private fun GeneratedAt() {
  Div(attrs = { attr("class", ClassNames.navbarGeneratedAt) }) {
    Text(germanDateTime())
  }
}
