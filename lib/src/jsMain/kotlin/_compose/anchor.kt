package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.A

/** Internal DOM adapter for an anchor with Kobweb modifier support. */
@Composable
fun KAnchor(
  href: String,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  A(href = href, attrs = modifier.toAttrs()) {
    content()
  }
}
