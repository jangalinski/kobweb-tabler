package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._compose.BaseCss.CONTAINER_XL
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
  content: @Composable () -> Unit
) = KDiv(modifier = modifier + CONTAINER_XL, content = content)
