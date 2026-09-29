package com.github.jangalinski.kobweb.tabler.avatar

import com.github.jangalinski.kobweb.tabler._foundation.css.cssClass

/**
 * Maps the avatar model to the Tabler CSS classes used by its renderers.
 */
internal data object AvatarCss {
  const val CLASS = "avatar"

  val avatar = cssClass(CLASS)
  val avatarList = cssClass("$CLASS-list")
  val avatarListStacked = cssClass("$CLASS-list-stacked")
}
