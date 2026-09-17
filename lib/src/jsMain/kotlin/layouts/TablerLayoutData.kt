package com.github.jangalinski.kobweb.tabler.models

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerNavigation
import com.varabyte.kobweb.compose.foundation.layout.RowScope

/**
 * Route-scoped content supplied to the shared Tabler Kobweb layout.
 *
 * The data feeds the preview's shared `BEGIN NAVBAR`/`BEGIN SIDEBAR` and
 * `BEGIN FOOTER` regions. Keeping those slots in route data allows a site to
 * choose active navigation and footer content before Kobweb composes the
 * layout, while the shell structure remains owned by the library.
 */
data class TablerLayoutData(
  /**
   * Legacy combined navigation rendered in the preview's `BEGIN NAVBAR` or
   * `BEGIN SIDEBAR` region when [sidebar] and [navbar] are both absent.
   */
  val navigation: TablerNavigation = TablerNavigation.None,
  /** Footer content rendered by the shared Tabler shell. */
  val footer: @Composable RowScope.() -> Unit = {},
  /** Explicit sidebar content for the preview's `BEGIN SIDEBAR` region. */
  val sidebar: TablerNavigation.SidebarNavigation? = null,
  /** Explicit navbar content for the preview's `BEGIN NAVBAR` region. */
  val navbar: TablerNavigation.HeaderNavigation? = null,
)

/**
 * Route metadata consumed by the shared Tabler page-header block.
 *
 * Kobweb supplies this through route initialization so the shared layout can
 * render the preview's `BEGIN PAGE HEADER` region consistently for every page.
 */
data class TablerPageMeta(
  val title: String,
  val subtitle: String? = null,
  /** Breadcrumbs rendered above the page title in the shared header. */
  val breadcrumbs: List<BreadcrumbItem> = emptyList(),
)
