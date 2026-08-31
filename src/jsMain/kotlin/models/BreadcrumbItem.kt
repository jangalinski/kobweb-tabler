package io.github.jangalinski.kotlin.kobweb.tabler.models

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
