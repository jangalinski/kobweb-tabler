package com.github.jangalinski.kobweb.tabler.models

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerNavigation
import com.varabyte.kobweb.compose.foundation.layout.RowScope

/**
 * Route-scoped content supplied to the shared Tabler Kobweb layout.
 *
 * [activeRoute] selects configured navigation. The optional navigation and
 * footer slots override the defaults supplied by `TablerSiteConfig` for this
 * route, while the shell structure remains owned by the library.
 */
data class TablerLayoutData(
  /** Base-path-independent route used to select configured navigation. */
  val activeRoute: String,
  /**
   * Combined navigation rendered in the preview's `BEGIN NAVBAR` or
   * `BEGIN SIDEBAR` region when [sidebar] and [navbar] are both absent.
   */
  val navigation: TablerNavigation? = null,
  /** Footer content overriding the configured Tabler shell footer. */
  val footer: (@Composable () -> Unit)? = null,
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
