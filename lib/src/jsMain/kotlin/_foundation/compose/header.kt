package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Header

/**
 * Internal DOM adapter for a header landmark with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the header.
 * @param content composable header content.
 */
@Composable
fun KHeader(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Header(attrs = modifier.toAttrs()) {
    content()
  }
}
