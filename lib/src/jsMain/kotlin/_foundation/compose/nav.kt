package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Nav

/**
 * Internal DOM adapter for a navigation landmark with Kobweb modifier support.
 *
 * @param label accessible label written as `aria-label`.
 * @param modifier attributes, classes, and styles applied to the navigation landmark.
 * @param content composable navigation content.
 */
@Composable
fun KNav(label: String, modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
  Nav(attrs = modifier.toAttrs { attr("aria-label", label) }) {
    content()
  }
}
