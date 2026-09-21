package com.github.jangalinski.kobweb.tabler._app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.github.jangalinski.kobweb.tabler._foundation.url
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
  val footer: @Composable () -> Unit = {},
)

/**
 * The [TablerSiteConfig] visible to the current Compose subtree.
 *
 * The neutral default keeps individual Tabler components and layouts usable
 * without a [KobwebTablerApp] wrapper.
 */
val LocalTablerSiteConfig = staticCompositionLocalOf { TablerSiteConfig() }
