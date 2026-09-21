package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Button

/**
 * Internal DOM adapter for a button with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the button.
 * @param content composable button content.
 */
@Composable
fun KButton(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Button(attrs = modifier.toAttrs()) {
    content()
  }
}
