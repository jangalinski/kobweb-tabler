package com.github.jangalinski.tabweb.site

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.jangalinski.tabweb.Tabweb.KobwebTablerApp
import com.github.jangalinski.tabweb._app.TablerShellConfig
import com.github.jangalinski.tabweb._app.TablerSiteConfig
import com.varabyte.kobweb.core.App
import kotlinx.browser.document

/**
 * Installs the Kobweb application wrapper for the repository documentation site.
 */
@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
  LaunchedEffect(Unit) {
    document.body?.className = "bg-body"
  }

  KobwebTablerApp(
    site = TablerSiteConfig(
      shell = TablerShellConfig(
        navbar = ::siteNavbar,
        navbarActions = { SiteThemeToggle() },
        footer = siteFooter(),
      ),
    ),
  ) {
    content()
  }
}
