package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Span

@Composable
fun KSpan(modifier: Modifier = Modifier, text: String) {
  KSpan(modifier) {
    KText(text)
  }
}

/** Internal DOM adapter for a span with Kobweb modifier support. */
@Composable
fun KSpan(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Span(attrs = modifier.toAttrs()) {
    content()
  }
}
