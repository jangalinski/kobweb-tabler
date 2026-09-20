package com.github.jangalinski.kobweb.tabler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.github.jangalinski.kobweb.tabler.components.TablerNavigation
import com.github.jangalinski.kobweb.tabler.models.url
import com.github.jangalinski.kobweb.tabler.navbar.TablerBrand
import com.github.jangalinski.kobweb.tabler.navbar.TablerNavbarFactory
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.core.AppGlobals

/**
 * Static, site-specific defaults for the Tabler page shell.
 *
 * Presentation settings that may change while the application runs belong in
 * [TablerSettings], not in this configuration.
 */
data class TablerSiteConfig(
  val shell: TablerShellConfig = TablerShellConfig(),
)

/** Shared page-shell defaults supplied by [TablerSiteConfig]. */
data class TablerShellConfig(
  val brand: TablerBrand.Brand = TablerBrand.Brand.Logo(
    image = url("/kobweb-tabler/kobweb-tabler-logo.svg"),
    caption = AppGlobals["title"] ?: "kobweb-tabler"
  ),
  /** Primary navigation rendered below the brand header for the active route. */
  val navbar: TablerNavbarFactory = TablerNavbarFactory.None,
  /** Actions rendered at the right side of the navbar's first row. */
  val navbarActions: @Composable () -> Unit = {},
  /**
   * Legacy shell navigation retained while the sidebar is migrated.
   *
   * New navbar implementations use [navbar].
   */
  @Deprecated("Use navbar for new navbar work.")
  val navigation: TablerNavigationFactory = TablerNavigationFactory.None,
  val footer: @Composable () -> Unit = {},
)

/** Creates navigation for the route currently rendered by the shared layout. */
fun interface TablerNavigationFactory {

  /** Creates the navigation model for [activeRoute]. */
  fun create(activeRoute: String): TablerNavigation

  companion object {
    /** A factory that renders no navigation. */
    val None = TablerNavigationFactory { TablerNavigation.None }
  }
}

/**
 * The [TablerSiteConfig] visible to the current Compose subtree.
 *
 * The neutral default keeps individual Tabler components and layouts usable
 * without a [KobwebTablerApp] wrapper.
 */
val LocalTablerSiteConfig = staticCompositionLocalOf { TablerSiteConfig() }
