package com.github.jangalinski.kobweb.tabler.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Main

/**
 * Renders the semantic main-content block of a Tabler page.
 *
 * This is the preview's `<!-- BEGIN PAGE BODY -->` region and emits
 * `<main id="content" class="page-body">`. The stable `content` id supports
 * the preview's skip link, while [ContainerXL] preserves Tabler's standard
 * content width. In a Kobweb layout, route content is supplied through
 * [content] and remains inside the shared page shell.
 *
 * @param modifier additional attributes or classes applied to the `<main>`
 *   element; `page-body` is always added.
 * @param content route content rendered inside the standard Tabler container.
 */
@Composable
fun TablerPageBody(
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit,
) {
  Main(attrs = modifier.then(Modifier.classNames("page-body")).toAttrs {
    attr("id", "content")
  }) {
    ContainerXL {
      content()
    }
  }
}
