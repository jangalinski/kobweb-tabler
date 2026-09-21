package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Div

/**
 * Internal DOM adapter for a div with class names.
 *
 * @param classNames CSS class names applied to the div.
 * @param content composable div content.
 */
@Composable
fun KDiv(vararg classNames: String, content: @Composable () -> Unit) = KDiv(
  modifier = Modifier.classNames(*classNames),
  content = content
)

/**
 * Internal DOM adapter for a div with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the div.
 * @param content composable div content.
 */
@Composable
fun KDiv(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Div(attrs = modifier.toAttrs()) {
    content()
  }
}
