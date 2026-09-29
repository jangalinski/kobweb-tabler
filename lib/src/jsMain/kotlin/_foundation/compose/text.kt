package com.github.jangalinski.tabweb._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
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
