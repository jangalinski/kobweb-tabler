package com.github.jangalinski.kobweb.tabler.avatar

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.github.jangalinski.kobweb.tabler._foundation.takeIf
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * A Tabler avatar list component keeps all [Avatar]s in the same line together.
 */
interface AvatarList : Tabler.Component {
  companion object {
    /**
     * Creates a list containing one configured [Avatar].
     */
    operator fun invoke(
      avatars: Avatar,
      stacked: Boolean = false,
      size: AvatarListSize = AvatarListSize.DEFAULT,
    ): AvatarList = invoke(stacked, size, listOf(avatars))

    operator fun invoke(
      stacked: Boolean = false,
      size: AvatarListSize = AvatarListSize.DEFAULT,
      vararg avatars: Avatar
    ) : AvatarList = invoke(stacked, size, avatars.toList())

    operator fun invoke(
      stacked: Boolean = false,
      size: AvatarListSize = AvatarListSize.DEFAULT,
      avatars: List<Avatar>
    ) : AvatarList = object : AvatarList {
      override val stacked: Boolean = stacked
      override val size: AvatarListSize = size
      override val avatars: List<Avatar> = avatars
    }
  }

  val stacked: Boolean
  val size: AvatarListSize
  val avatars: List<Avatar>

  @Composable
  override fun invoke(modifier: Modifier) {
    KDiv(modifier = AvatarCss.avatarList + AvatarCss.avatarListStacked.takeIf(stacked) + size + modifier) {
      avatars.forEach { it.invoke() }
    }
  }
}
