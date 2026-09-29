package com.github.jangalinski.kobweb.tabler.avatar

import com.github.jangalinski.kobweb.tabler._foundation.css.cssClass

/** Internal mapping between the avatar model and Tabler CSS classes. */
internal object AvatarCss {
  const val className = "avatar"

  val avatar = cssClass(className)
  val avatarList = cssClass("$className-list")
  val avatarListStacked = cssClass("$className-list-stacked")
}
