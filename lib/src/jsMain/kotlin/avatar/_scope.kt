package com.github.jangalinski.kobweb.tabler.avatar

import com.github.jangalinski.kobweb.tabler._foundation.Image
import com.github.jangalinski.kobweb.tabler._foundation.Initials
import com.github.jangalinski.kobweb.tabler._foundation.TabwebDsl
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon

/**
 * Provides the children for an avatar list DSL.
 */
@TabwebDsl
class AvatarListScope internal constructor() {
  internal val avatars = mutableListOf<Avatar>()

  /**
   * Adds an already configured [Avatar] to this list.
   */
  fun avatar(avatar: Avatar) {
    avatars += avatar
  }

  /**
   * Adds an avatar whose content is selected by the nested [AvatarScope].
   */
  fun avatar(
    size: AvatarSize = AvatarSize.DEFAULT,
    color: BackgroundColor = BackgroundColor.DEFAULT,
    style: AvatarStyle = AvatarStyle.DEFAULT,
    content: AvatarScope.() -> Unit,
  ) {
    avatars += AvatarScope(size, color, style).apply(content).build()
  }
}

/**
 * Selects the typed content of an avatar declared in an [AvatarListScope].
 */
@TabwebDsl
class AvatarScope internal constructor(
  private val size: AvatarSize,
  private val color: BackgroundColor,
  private val style: AvatarStyle,
) {
  private var avatar: Avatar? = null

  /**
   * Selects an icon avatar with the enclosing avatar properties.
   */
  fun icon(icon: Icon) {
    setAvatar(Avatar(content = icon, size = size, color = color, style = style))
  }

  /**
   * Selects an initials avatar with the enclosing avatar properties.
   */
  fun initials(initials: Initials) {
    setAvatar(Avatar(content = initials, size = size, color = color, style = style))
  }

  /**
   * Selects an image avatar with the enclosing avatar properties.
   */
  fun image(image: Image.Resource) {
    setAvatar(Avatar(content = image, size = size, style = style))
  }

  internal fun build(): Avatar = requireNotNull(avatar) {
    "An avatar block must select exactly one of image, icon, or initials."
  }

  private fun setAvatar(avatar: Avatar) {
    check(this.avatar == null) {
      "An avatar block may select only one of image, icon, or initials."
    }
    this.avatar = avatar
  }
}
