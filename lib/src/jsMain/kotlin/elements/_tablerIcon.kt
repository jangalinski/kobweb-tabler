package com.github.jangalinski.kobweb.tabler.elements

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.I

/**
 * Renders a [TablerIcon] using the given [modifier].
 */
@Composable
operator fun TablerIcon.invoke(modifier: Modifier = Modifier) {
  I(attrs = modifier.then(this).toAttrs())
}
