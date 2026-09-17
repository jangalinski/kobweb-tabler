package com.github.jangalinski.kobweb.tabler.site

import com.github.jangalinski.kobweb.tabler.models.TablerLayoutData
import com.github.jangalinski.kobweb.tabler.models.TablerPageMeta

/** Provides the site-owned sidebar, navbar, and footer to the shared Tabler layout. */
fun siteLayoutData(activeRoute: String): TablerLayoutData =
  TablerLayoutData(
    sidebar = siteSidebar(activeRoute),
    navbar = siteNavigation(activeRoute),
    footer = siteFooter(),
  )

/** Builds the page header metadata consumed by the shared Tabler layout. */
fun sitePageMeta(title: String, subtitle: String? = null): TablerPageMeta =
  TablerPageMeta(title = title, subtitle = subtitle)
