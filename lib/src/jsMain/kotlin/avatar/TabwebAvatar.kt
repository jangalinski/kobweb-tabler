package com.github.jangalinski.kobweb.tabler.avatar

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.*
import com.github.jangalinski.kobweb.tabler._foundation.TabwebDsl
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.css.cssClass
import com.github.jangalinski.kobweb.tabler.avatar.TabwebAvatar.AvatarListScope
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * A Tabler avatar component.
 */
data object TabwebAvatar : AvatarComposable {
  const val CLASS = "avatar"

  @Composable
  override fun avatars(
    stacked: Boolean,
    modifier: Modifier,
    content: @Composable AvatarListScope.() -> Unit
  ) {
    val scope = AvatarListScope()
    KDiv(modifier = CSS.AVATAR_LIST + CSS.AVATAR_LIST_STACKED.takeIf(stacked) + modifier) {
      scope.content()
    }
  }

  @TabwebDsl
  class AvatarScope internal constructor() {



    @Composable
    fun avatar() {

    }
  }

  @TabwebDsl
  class AvatarListScope internal constructor() {
    private val avatars = mutableListOf<Avatar>()

    operator fun plus(avatar: Avatar) {
      avatars.add(avatar)
    }



    //val stacked: Modifier get() = Modifier.classNames(CSS.AVATAR_LIST_STACKED)
  }

}


internal interface AvatarComposable {
  @Composable
  fun avatars(
    stacked: Boolean = false,
    modifier: Modifier = Modifier,
    content: @Composable AvatarListScope.() -> Unit
  )
}
