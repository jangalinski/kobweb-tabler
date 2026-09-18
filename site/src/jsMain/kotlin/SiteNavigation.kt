package com.github.jangalinski.kobweb.tabler.site

import com.github.jangalinski.kobweb.tabler.components.TablerNavigation.HeaderNavigation
import com.github.jangalinski.kobweb.tabler.components.TablerNavigation.SidebarNavigation
import com.github.jangalinski.kobweb.tabler.models.Image

/** Builds the navbar while keeping route constants independent of the deployment base path. */
fun siteNavigation(activeRoute: String): HeaderNavigation =
  HeaderNavigation(
    title = "kobweb-tabler",
    caption = "Documentation",
    logo = Image.ImageResource(
      resource = "kobweb-tabler-wordmark.svg",
      altText = "kobweb-tabler",
    ),
    href = SiteUi.href(SiteRoutes.Home),
    items = SiteUi.navigation(activeRoute) {
      a(SiteRoutes.Home, "Home")
      a(SiteRoutes.Components, "Components")
      menu(SiteRoutes.Elements, "Elements") {
        a(SiteRoutes.Elements, "Overview")
        a(SiteRoutes.Tables, "Tables")
      }
    },
  )
