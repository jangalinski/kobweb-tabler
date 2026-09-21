package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.I
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Main
import org.jetbrains.compose.web.dom.Ol
import org.jetbrains.compose.web.dom.P

/**
 * Internal DOM adapter for an image with Kobweb modifier support.
 *
 * @param src image source URL.
 * @param alt alternative text for the image.
 * @param modifier attributes, classes, and styles applied to the image.
 */
@Composable
fun KImg(src: String, alt: String, modifier: Modifier = Modifier) {
  Img(src = src, alt = alt, attrs = modifier.toAttrs())
}

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
 * Internal DOM adapter for a level-three heading with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the heading.
 * @param content composable heading content.
 */
@Composable
fun KH3(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  H3(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for a paragraph with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the paragraph.
 * @param content composable paragraph content.
 */
@Composable
fun KP(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  P(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for an ordered list with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the list.
 * @param content composable list content.
 */
@Composable
fun KOl(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Ol(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for the page's main landmark with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the main landmark.
 * @param content composable main content.
 */
@Composable
fun KMain(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Main(attrs = modifier.toAttrs()) { content() }
}

/**
 * Internal DOM adapter for an inline icon element with Kobweb modifier support.
 *
 * @param modifier attributes, classes, and styles applied to the icon element.
 */
@Composable
fun KI(modifier: Modifier = Modifier) {
  I(attrs = modifier.toAttrs())
}
