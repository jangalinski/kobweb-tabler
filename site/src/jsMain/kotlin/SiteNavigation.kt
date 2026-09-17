package com.github.jangalinski.kobweb.tabler.site

import com.github.jangalinski.kobweb.tabler.components.TablerNavigation.HeaderNavigation
import com.github.jangalinski.kobweb.tabler.components.TablerNavigation.SidebarNavigation
import com.github.jangalinski.kobweb.tabler.models.Image
import com.github.jangalinski.kobweb.tabler.models.navigationItems
import com.varabyte.kobweb.navigation.BasePath

/** Builds the navbar while keeping route constants independent of the deployment base path. */
fun siteNavigation(activeRoute: String): HeaderNavigation =
  HeaderNavigation(
    title = "kobweb-tabler",
    caption = "Documentation",
    logo = Image.ImageResource(
      resource = "kobweb-tabler-logo.svg",
      altText = "kobweb-tabler",
    ),
    href = BasePath.prependTo(SiteRoutes.Home),
    items = siteNavigationItems(activeRoute),
  )

/** Builds the menu items shared by the vertical and horizontal arrangements. */
private fun siteNavigationItems(activeRoute: String) =
  navigationItems {
    link(
      name = "Home",
      href = BasePath.prependTo(SiteRoutes.Home),
      active = activeRoute == SiteRoutes.Home,
    )
    link(
      name = "Components",
      href = BasePath.prependTo(SiteRoutes.Components),
      active = activeRoute == SiteRoutes.Components,
    )
    dropdown(
      name = "Elements",
      href = BasePath.prependTo(SiteRoutes.Elements),
      active = activeRoute.startsWith(SiteRoutes.Elements),
      items = {
        link(
          name = "Overview",
          href = BasePath.prependTo(SiteRoutes.Elements),
          active = activeRoute == SiteRoutes.Elements,
        )
        link(
          name = "Tables",
          href = BasePath.prependTo(SiteRoutes.Tables),
          active = activeRoute == SiteRoutes.Tables,
        )
      },
    )
  }

/** Builds the site-owned brand block for the preview's `BEGIN SIDEBAR` region. */
fun siteSidebar(activeRoute: String): SidebarNavigation =
  SidebarNavigation(
    title = "kobweb-tabler",
    caption = "Documentation",
    logo = Image.ImageResource(
      resource = "kobweb-tabler-logo.svg",
      altText = "kobweb-tabler",
    ),
    href = BasePath.prependTo(SiteRoutes.Home),
    items = siteNavigationItems(activeRoute),
  )
