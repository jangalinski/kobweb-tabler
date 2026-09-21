package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.A

/**
 * Internal DOM adapter for an anchor with Kobweb modifier support.
 *
 * @param href destination URL written to the anchor's `href` attribute.
 * @param modifier attributes, classes, and styles applied to the anchor.
 * @param content composable anchor content.
 */
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
