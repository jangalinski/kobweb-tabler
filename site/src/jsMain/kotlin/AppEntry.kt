package com.github.jangalinski.kobweb.tabler.site

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.KobwebApp
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

  KobwebApp {
    content()
  }
}
