package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*


/**
 * Internal DOM adapter for a level-one heading with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the heading.
 * @param content composable heading content.
 */
@Composable
fun KH1(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  H1(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for a level-two heading with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the heading.
 * @param content composable heading content.
 */
@Composable
fun KH2(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  H2(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for a level-three heading with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the heading.
 * @param content composable heading content.
 */
@Composable
fun KH3(modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
  H3(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for a level-four heading with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the heading.
 * @param content composable heading content.
 */
@Composable
fun KH4(modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
  H4(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for a level-five heading with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the heading.
 * @param content composable heading content.
 */
@Composable
fun KH5(modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
  H5(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for a level-six heading with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the heading.
 * @param content composable heading content.
 */
@Composable
fun KH6(modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
  H6(attrs = modifier.toAttrs()) { content() }
}
