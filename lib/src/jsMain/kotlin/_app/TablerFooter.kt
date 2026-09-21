package com.github.jangalinski.kobweb.tabler._app

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.ContainerXL
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Footer

/**
 * Renders the footer block of a Tabler page shell.
 *
 * This corresponds to the preview's `<!-- BEGIN FOOTER -->` region and emits
 * the transparent, print-hidden Tabler footer around [ContainerXL]. It is a
 * slot so a Kobweb application can supply site-specific links, attribution,
 * or status content without replacing the shell structure.
 *
 * @param modifier additional attributes or classes applied to the `<footer>`
 *   element; the standard Tabler footer classes are always added.
 * @param content footer content rendered inside the standard Tabler container.
 */
@Composable
fun TablerFooter(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Footer(attrs = modifier.then(Modifier.classNames("footer", "footer-transparent", "d-print-none")).toAttrs()) {
    ContainerXL {
      content()
    }
  }
}
