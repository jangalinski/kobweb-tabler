package com.github.jangalinski.tabweb.avatar

import com.github.jangalinski.tabweb._foundation.Image
import com.github.jangalinski.tabweb._foundation.Initials
import com.github.jangalinski.tabweb._foundation.TabwebDsl
import com.github.jangalinski.tabweb._foundation.modifier.BackgroundColor
import com.github.jangalinski.tabweb.icon.Icon

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
   *
   * @param icon the icon shown by the avatar.
   * @return `Unit` after the icon content has been selected.
   */
  fun icon(icon: Icon) {
    setAvatar(Avatar(content = icon, size = size, color = color, style = style))
  }

  /**
   * Selects an initials avatar with the enclosing avatar properties.
   *
   * @param initials the initials shown by the avatar.
   * @return `Unit` after the initials content has been selected.
   */
  fun initials(initials: Initials) {
    setAvatar(Avatar(content = initials, size = size, color = color, style = style))
  }

  /**
   * Selects an image avatar with the enclosing avatar properties.
   *
   * @param image the resource image used as the avatar background.
   * @return `Unit` after the image content has been selected.
   */
  fun image(image: Image.Resource) {
    setAvatar(Avatar(content = image, size = size, style = style))
  }
  /**
   * Selects an image avatar with the enclosing avatar properties.
   *
   * @param image the resource image used as the avatar background.
   * @return `Unit` after the image content has been selected.
   */
  fun image(url: String) {
    setAvatar(Avatar(content = Image(url), size = size, style = style))
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
