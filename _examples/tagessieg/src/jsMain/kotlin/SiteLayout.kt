package io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg

import io.github.jangalinski.kotlin.kobweb.tabler.models.BreadcrumbItem
import io.github.jangalinski.kotlin.kobweb.tabler.models.TablerLayoutData
import io.github.jangalinski.kotlin.kobweb.tabler.models.TablerPageMeta

/**
 * Builds the layout data consumed by the shared Tabler shell.
 *
 * This keeps the site-specific active-route mapping in one place while leaving the
 * shared layout generic and reusable. The footer is also site-provided so the shared
 * library does not need to know about repository URLs.
 */
fun siteLayoutData(activeRoute: String): TablerLayoutData =
  TablerLayoutData(
    navigation = sharedNavigation(activeRoute),
    footer = siteFooter(),
  )

/**
 * Builds the page metadata object that the shared layout uses for titles and subtitles.
 *
 * The page metadata is populated from `@InitRoute` so the layout can read it before
 * the page body renders.
 */
fun sitePageMeta(title: String, subtitle: String? = null): TablerPageMeta =
  sitePageMeta(title = title, subtitle = subtitle, breadcrumbs = emptyList())

/**
 * Builds the page metadata object that the shared layout uses for titles, subtitles, and breadcrumbs.
 *
 * The page metadata is populated from `@InitRoute` so the layout can read it before
 * the page body renders.
 */
fun sitePageMeta(
  title: String,
  subtitle: String? = null,
  breadcrumbs: List<BreadcrumbItem> = emptyList(),
): TablerPageMeta =
  TablerPageMeta(
    title = title,
    subtitle = subtitle,
    breadcrumbs = breadcrumbs,
  )
