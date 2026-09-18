package com.github.jangalinski.kobweb.tabler.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Footer
import org.w3c.dom.HTMLDivElement


@Composable
fun KDiv(vararg classNames: String, content: ContentBuilder<HTMLDivElement>? = null) {
  KDiv(Modifier.classNames(*classNames), content = content)
}

@Composable
fun KDiv(modifier: Modifier = Modifier, content: ContentBuilder<HTMLDivElement>? = null) {
  Div(attrs = modifier.toAttrs(), content = content)
}

@Composable
fun KFooter(
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit,
) {
  Footer(attrs = modifier.then(Modifier.classNames("footer", "footer-transparent", "d-print-none")).toAttrs()) {
    ContainerXL {
      content()
    }
  }
}
