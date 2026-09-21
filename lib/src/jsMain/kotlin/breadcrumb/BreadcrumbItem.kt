package com.github.jangalinski.kobweb.tabler.breadcrumb

/**
 * A single crumb in a Tabler breadcrumb trail.
 *
 * Crumbs may be clickable or active.
 */
data class BreadcrumbItem(
  val label: String,
  val href: String? = null,
  val active: Boolean = false,
)
