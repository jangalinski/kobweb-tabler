package com.github.jangalinski.kobweb.tabler.avatar

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.github.jangalinski.kobweb.tabler._foundation.compose.KSpan
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.github.jangalinski.kobweb.tabler._foundation.css.cssClass
import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.github.jangalinski.kobweb.tabler._foundation.model.Initials
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon
import com.varabyte.kobweb.compose.ui.Modifier


/**
 * An avatar shows a user's picture, initials or an icon in a fixed-size
 * circle or square, with sizes, statuses and stacked lists.
 */
fun interface Avatar : Tabler.Component {

  val style: AvatarStyle get() = AvatarStyle.CIRCLE
  val color: BackgroundColor get() = BackgroundColor.BASE.WHITE
  val size: AvatarSize get() = AvatarSize.M

}

fun interface Avatars : Tabler.Component

val cssAvatar = cssClass("avatar")
val cssAvatarList = cssClass("avatar-list")

data class IconAvatar(
  val icon: Icon,
  override val style: AvatarStyle = AvatarStyle.CIRCLE,
  override val color: BackgroundColor = BackgroundColor.DEFAULT,
  override val size: AvatarSize = AvatarSize.M
) : Avatar {

  @Composable
  override fun invoke(modifier: Modifier) {

    KSpan(modifier = modifier + cssAvatar + style + color + size) {
      icon()
    }
  }

}

data class InitialsAvatar(
  val initials: Initials,
  override val style: AvatarStyle = AvatarStyle.CIRCLE,
  override val color: BackgroundColor = BackgroundColor.DEFAULT,
  override val size: AvatarSize = AvatarSize.M
) : Avatar {

  @Composable
  override fun invoke(modifier: Modifier) {
    KSpan(modifier = modifier + cssAvatar + style + color + size) {
      KText(initials.get())
    }
  }

}
