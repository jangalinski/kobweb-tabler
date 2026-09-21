package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.P

/**
 * Internal DOM adapter for a paragraph with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the paragraph.
 * @param content composable paragraph content.
 */
@Composable
fun KP(modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
  P(attrs = modifier.toAttrs()) { content() }
}
