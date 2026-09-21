package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Main

/**
 * Internal DOM adapter for the page's main landmark with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the main landmark.
 * @param content composable main content.
 */
@Composable
fun KMain(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Main(attrs = modifier.toAttrs()) { content() }
}
