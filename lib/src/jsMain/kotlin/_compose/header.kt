package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Header

@Composable
fun KHeader(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Header(attrs = modifier.toAttrs()) {
    content()
  }
}
