package com.github.jangalinski.kobweb.tabler.site

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._app.LocalTablerAppState
import com.github.jangalinski.kobweb.tabler._app.TablerTheme
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon
import com.github.jangalinski.kobweb.tabler._foundation.url
import com.github.jangalinski.kobweb.tabler.navbar.TablerNavbarData
import com.github.jangalinski.kobweb.tabler.navbar.TablerNavbarItem
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div

/** Builds the sample primary navigation shown by the documentation site. */
fun siteNavbar(activeRoute: String) = TablerNavbarData(
  items = listOf(
    TablerNavbarItem.Link(
      url = url(SiteRoutes.Home),
      title = "Home",
      icon = TablerIcon.TI_HOME,
      active = activeRoute == SiteRoutes.Home,
    ),
    TablerNavbarItem.Section(
      title = "Infrastructure",
      icon = TablerIcon.TI_SERVER,
      items = listOf(
        TablerNavbarItem.Link(
          url = url(SiteRoutes.Components),
          title = "Components",
          caption = "Reusable UI building blocks",
          active = activeRoute == SiteRoutes.Components,
        ),
        TablerNavbarItem.Link(
          url = url(SiteRoutes.Elements),
          title = "Elements",
          caption = "Low-level Tabler elements",
          active = activeRoute == SiteRoutes.Elements,
        ),
        TablerNavbarItem.Link(
          url = url(SiteRoutes.Tables),
          title = "Tables",
          caption = "Tabler table examples",
          active = activeRoute == SiteRoutes.Tables,
          icon = TablerIcon.TI_TABLE,
        ),
      ),
    ),
    TablerNavbarItem.Section(
      title = "Plugins",
      icon = TablerIcon.TI_PUZZLE,
      items = listOf(
        TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "All plugins"),
        TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "Marketplace"),
        TablerNavbarItem.Section(
          title = "Installed",
          items = listOf(
            TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "Analytics"),
            TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "Backups"),
            TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "Monitoring"),
          ),
        ),
        TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "Updates"),
        TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "Plugin settings"),
        TablerNavbarItem.Link(url = url(SiteRoutes.Home), title = "Developer tools"),
      ),
      columns = 2,
    ),
    TablerNavbarItem.Link(
      url = url(SiteRoutes.Home),
      title = "Help",
      caption = "Documentation and support",
      icon = TablerIcon.TI_HELP_CIRCLE,
    ),
  ),
)

/** Renders the light/dark mode control in the site navbar's first row. */
@Composable
fun SiteThemeToggle() {
  val tabler = LocalTablerAppState.current
  val darkMode = tabler.settings.theme == TablerTheme.Dark
  val targetMode = if (darkMode) TablerTheme.Light else TablerTheme.Dark
  val targetName = if (darkMode) "light" else "dark"

  Div(attrs = { attr("class", "d-none d-md-flex me-3") }) {
    Div(attrs = { attr("class", "nav-item") }) {
      A(
        href = "#",
        attrs = {
          attr("class", "nav-link px-0")
          attr("title", "Enable $targetName mode")
          attr("aria-label", "Enable $targetName mode")
          onClick {
            it.preventDefault()
            tabler.setTheme(targetMode)
          }
        },
      ) {
        if (darkMode) TablerIcon.TI_SUN() else TablerIcon.TI_MOON()
      }
    }
  }
}
