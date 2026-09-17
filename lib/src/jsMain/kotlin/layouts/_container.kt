package com.github.jangalinski.kobweb.tabler.layouts

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier


/**
 * Applies Tabler's standard extra-large content container.
 *
 * This is the repeated `.container-xl` block visible inside the preview's
 * page header, page body, and footer regions. It is kept as a small Kobweb
 * layout primitive so those regions share the same responsive width.
 */
@Composable
fun ContainerXL(
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit,
) {
  Row(modifier = modifier.then(ClassNames.containerXl.modifier())) {
    content()
  }
}
