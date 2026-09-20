package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._compose.BaseCss.PRINT_NONE
import com.github.jangalinski.kobweb.tabler.styles.cssClass
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Footer

private val CSS_FOOTER = cssClass("footer")
private val CSS_FOOTER_TRANSPARENT = cssClass("footer")

@Composable
fun KFooter(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Footer(attrs = (modifier + CSS_FOOTER + CSS_FOOTER_TRANSPARENT + PRINT_NONE).toAttrs()) {
    ContainerXL {
      content()
    }
  }
}
