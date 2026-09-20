package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Div

@Composable
fun KDiv(vararg classNames: String, content: @Composable () -> Unit) = KDiv(
  modifier = Modifier.classNames(*classNames),
  content = content
)

@Composable
fun KDiv(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Div(attrs = modifier.toAttrs()) {
    content()
  }
}
