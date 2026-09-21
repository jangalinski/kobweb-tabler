package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Span

/**
 * Renders text in a span with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the span.
 * @param text text rendered inside the span.
 */
@Composable
fun KSpan(modifier: Modifier = Modifier, text: String) {
  KSpan(modifier) {
    KText(text)
  }
}

/**
 * Internal DOM adapter for a span with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the span.
 * @param content composable span content.
 */
@Composable
fun KSpan(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Span(attrs = modifier.toAttrs()) {
    content()
  }
}
