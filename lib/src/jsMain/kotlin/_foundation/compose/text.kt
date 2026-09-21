package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Text

/**
 * Internal DOM adapter for text content.
 *
 * @param value text value rendered into the current DOM node.
 */
@Composable
fun KText(value: String) {
  Text(value)
}
