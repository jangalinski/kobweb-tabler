package com.github.jangalinski.kobweb.tabler.site

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.layouts.TablerLayout
import com.github.jangalinski.kobweb.tabler.models.TablerLayoutData
import com.github.jangalinski.kobweb.tabler.models.TablerPageMeta
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout

/**
 * Application-module adapter for the reusable library layout.
 *
 * Kobweb associates page layouts during the site's KSP pass, so the adapter keeps
 * that association local while delegating all shell rendering to the library.
 */
@Layout
@Composable
fun SiteLayout(ctx: PageContext, content: @Composable () -> Unit) {
  TablerLayout(ctx, content)
}

/** Provides the site-owned navbar and footer to the shared Tabler layout. */
fun siteLayoutData(activeRoute: String): TablerLayoutData =
  TablerLayoutData(
    navigation = siteNavigation(activeRoute),
    footer = siteFooter(),
  )

/** Builds the page header metadata consumed by the shared Tabler layout. */
fun sitePageMeta(title: String, subtitle: String? = null): TablerPageMeta =
  TablerPageMeta(title = title, subtitle = subtitle)
