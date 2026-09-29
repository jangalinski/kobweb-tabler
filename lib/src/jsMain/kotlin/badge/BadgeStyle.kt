package com.github.jangalinski.kobweb.tabler.badge

import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * Enumerates the supported Tabler visual treatments for a [Badge].
 */
enum class BadgeStyle {
  /**
   * A filled badge with the foreground required by its background treatment.
   */
  SOLID,

  /**
   * A transparent badge with a colored outline and foreground.
   */
  OUTLINE,
  ;

  companion object {
    val DEFAULT = SOLID
  }

  internal fun toBadgeModifier(color: BackgroundColor): Modifier = when (this) {
    SOLID -> color as? BackgroundColor.LIGHT ?: (color + color.textFg)
    OUTLINE -> BadgeCss.outline + BadgeCss.outlineText(color)
  }
}
