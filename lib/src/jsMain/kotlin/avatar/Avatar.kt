package com.github.jangalinski.kobweb.tabler.avatar

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Image
import com.github.jangalinski.kobweb.tabler._foundation.Initials
import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.github.jangalinski.kobweb.tabler._foundation.compose.KSpan
import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.github.jangalinski.kobweb.tabler._foundation.modifier
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * An avatar shows a user's picture, initials or an icon in a fixed-size
 * circle or square, with sizes, statuses and stacked lists.
 */
interface Avatar : Tabler.Component {

  companion object {
    operator fun invoke(
      content: Icon,
      size: AvatarSize = AvatarSize.DEFAULT,
      color: BackgroundColor = BackgroundColor.DEFAULT,
      style: AvatarStyle = AvatarStyle.DEFAULT
    ) : Avatar = object : Avatar {
      override val content: AvatarContent = AvatarContent.Icon(content)
      override val size: AvatarSize = size
      override val color: BackgroundColor = color
      override val style: AvatarStyle = style
    }

    operator fun invoke(
      content: Initials,
      size: AvatarSize = AvatarSize.DEFAULT,
      color: BackgroundColor = BackgroundColor.DEFAULT,
      style: AvatarStyle = AvatarStyle.DEFAULT
    ) : Avatar = object : Avatar {
      override val content: AvatarContent = AvatarContent.Initials(content)
      override val size: AvatarSize = size
      override val color: BackgroundColor = color
      override val style: AvatarStyle = style
    }

    operator fun invoke(
      content: Image.Resource,
      size: AvatarSize = AvatarSize.DEFAULT,
      style: AvatarStyle = AvatarStyle.DEFAULT
    ) : Avatar = object : Avatar {
      override val content: AvatarContent = AvatarContent.Image(content)
      override val size: AvatarSize = size
      override val style: AvatarStyle = style
    }
  }

  val content: AvatarContent
  val size: AvatarSize get() = AvatarSize.DEFAULT
  val color: BackgroundColor get() = BackgroundColor.DEFAULT
  val style: AvatarStyle get() = AvatarStyle.DEFAULT

  @Composable
  override fun invoke(modifier: Modifier) {
    val modifiers = AvatarCss.avatar + size + style

    when (content) {
      is AvatarContent.Image -> {
        val backgroundImage = (content as AvatarContent.Image).content.backgroundImage.modifier
        KSpan(modifier = modifiers + backgroundImage + modifier)
      }

      is AvatarContent.Icon -> KSpan(modifier = modifiers + color + modifier) {
        (content as AvatarContent.Icon).content.invoke()
      }

      is AvatarContent.Initials -> KSpan(modifier = modifiers + color + modifier) {
        (content as AvatarContent.Initials).content.invoke()
      }
    }
  }
}
