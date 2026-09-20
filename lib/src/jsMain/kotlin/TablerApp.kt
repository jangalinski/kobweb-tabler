package com.github.jangalinski.kobweb.tabler

import androidx.compose.runtime.*
import kotlinx.browser.document

/**
 * Creates one [TablerAppState] for the lifetime of its composition location.
 */
@Composable
fun rememberTablerAppState(initialSettings: TablerSettings = TablerSettings()): TablerAppState =
  remember { TablerAppState(initialSettings) }

/**
 * Provides static [site] defaults to Tabler composables in [content].
 *
 * @param site The static site configuration to provide.
 * @param content The content to provide the site configuration to.
 */
@Composable
fun ProvideTablerSiteConfig(
  site: TablerSiteConfig,
  content: @Composable () -> Unit,
) {
  CompositionLocalProvider(LocalTablerSiteConfig provides site) {
    content()
  }
}

/**
 * Provides [state] to Tabler composables and synchronizes its settings with
 * the document's Tabler attributes.
 */
@Composable
fun ProvideTablerAppState(
  state: TablerAppState,
  content: @Composable () -> Unit,
) {
  val settings = state.settings

  SideEffect {
    settings.applyToDocument()
  }

  CompositionLocalProvider(LocalTablerAppState provides state) {
    content()
  }
}

/** The [TablerAppState] visible to the current Compose subtree. */
val LocalTablerAppState = staticCompositionLocalOf<TablerAppState> {
  error("TablerAppState was not provided")
}

internal fun TablerSettings.applyToDocument() {
  document.documentElement?.let { html ->
    when (theme) {
      TablerTheme.System -> html.removeAttribute("data-bs-theme")
      TablerTheme.Light -> html.setAttribute("data-bs-theme", "light")
      TablerTheme.Dark -> html.setAttribute("data-bs-theme", "dark")
    }
  }
}
