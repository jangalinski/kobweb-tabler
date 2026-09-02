package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier

/**
 * Renders the Tabler page footer block.
 */
@Composable
fun TablerFooter(
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit,
) {
  Box(
    modifier = modifier.then(ClassNames.footer.modifier())
      .then(ClassNames.footerTransparent.modifier())
      .then(ClassNames.dPrintNone.modifier()),
  ) {
    Column(modifier = ClassNames.containerXl.modifier()) {
      content()
    }
  }
}
