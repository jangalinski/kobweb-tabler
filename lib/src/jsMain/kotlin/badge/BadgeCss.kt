package com.github.jangalinski.kobweb.tabler.badge

import com.github.jangalinski.kobweb.tabler._foundation.css.cssClass
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor

/**
 * Maps the badge model to the Tabler CSS classes used by its renderers.
 */
internal data object BadgeCss {
  val badge = cssClass("badge")
  val iconOnly = cssClass("badge-icononly")
  val badgeList = cssClass("badge-list")
  val outline = cssClass("badge-outline")

  fun outlineText(color: BackgroundColor) = cssClass("text-${color.value.removePrefix("bg-").removeSuffix("-lt")}")
}
