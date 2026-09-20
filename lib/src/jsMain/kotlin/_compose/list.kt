package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Ul

/** Internal DOM adapter for an unordered list with Kobweb modifier support. */
@Composable
fun KUl(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Ul(attrs = modifier.toAttrs()) {
    content()
  }
}

/** Internal DOM adapter for a list item with Kobweb modifier support. */
@Composable
fun KLi(modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
    Li(attrs = modifier.toAttrs()) {
        content()
    }
}
