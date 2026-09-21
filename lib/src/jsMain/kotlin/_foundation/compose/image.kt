package com.github.jangalinski.kobweb.tabler._foundation.compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Img

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
