package com.github.jangalinski.kobweb.tabler._app

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.ContainerXL
import com.github.jangalinski.kobweb.tabler._foundation.compose.KFooter
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

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
  KFooter(modifier, content)
}
