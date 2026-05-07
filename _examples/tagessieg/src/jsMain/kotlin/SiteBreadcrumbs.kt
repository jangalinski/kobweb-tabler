package net.janhoo.kotlin.kobweb.tabler.example.tagessieg

import net.janhoo.kotlin.kobweb.tabler.models.BreadcrumbItem

/**
 * Builds the breadcrumb trail for a given site route.
 *
 * The trail is derived from the same route constants that power the shared navigation
 * so the header breadcrumbs and top navigation stay visually and semantically aligned.
 */
fun siteBreadcrumbs(activeRoute: String): List<BreadcrumbItem> =
  when (activeRoute) {
    SiteRoutes.Root -> emptyList()
    SiteRoutes.Analyse -> listOf(
      BreadcrumbItem(label = "Home", href = siteHref(SiteRoutes.Root)),
      BreadcrumbItem(label = "Analysis", active = true),
    )
    SiteRoutes.Liga -> listOf(
      BreadcrumbItem(label = "Home", href = siteHref(SiteRoutes.Root)),
      BreadcrumbItem(label = "Liga", active = true),
    )
    SiteRoutes.Liga2023 -> ligaSeasonBreadcrumbs("2023")
    SiteRoutes.Liga2024 -> ligaSeasonBreadcrumbs("2024")
    SiteRoutes.Liga2025 -> ligaSeasonBreadcrumbs("2025")
    SiteRoutes.Liga2026 -> ligaSeasonBreadcrumbs("2026")
    else -> listOf(
      BreadcrumbItem(label = "Home", href = siteHref(SiteRoutes.Root)),
      BreadcrumbItem(label = activeRoute.removePrefix("/").ifBlank { "Page" }, active = true),
    )
  }

/**
 * Builds the breadcrumb trail for a Liga season page.
 */
private fun ligaSeasonBreadcrumbs(year: String): List<BreadcrumbItem> = listOf(
  BreadcrumbItem(label = "Home", href = siteHref(SiteRoutes.Root)),
  BreadcrumbItem(label = "Liga", href = siteHref(SiteRoutes.Liga)),
  BreadcrumbItem(label = year, active = true),
)
