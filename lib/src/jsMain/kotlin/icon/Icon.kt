package com.github.jangalinski.kobweb.tabler.icon

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Component
import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.github.jangalinski.kobweb.tabler._foundation.css.cssClass
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.I

val CSS_ICON = cssClass("icon")

/**
 * A Tabler icon component.
 *
 * @see [TablerIcon] for a list of all available icons.
 * @see https://docs.tabler.io/ui/components/icons for more information about Tabler icons.
 */
fun interface Icon : Component

@Composable
fun icon(icon: Modifier) = Icon { modifier ->
  val allModifier = CSS_ICON + icon + modifier

  I(attrs = allModifier.toAttrs())
}
