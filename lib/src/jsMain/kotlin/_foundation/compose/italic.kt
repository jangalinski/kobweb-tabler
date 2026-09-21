package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.I

/**
 * Internal DOM adapter for an inline icon element with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the icon element.
 */
@Composable
fun KI(modifier: Modifier = Modifier.Companion) {
  I(attrs = modifier.toAttrs())
}
