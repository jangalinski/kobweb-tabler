package com.github.jangalinski.kobweb.tabler.avatar

import com.github.jangalinski.kobweb.tabler._foundation.Image
import com.github.jangalinski.kobweb.tabler._foundation.Initials
import com.github.jangalinski.kobweb.tabler._foundation.TabwebComponentScope
import com.github.jangalinski.kobweb.tabler._foundation.TabwebDsl
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon

/**
 * Provides the children for an avatar list DSL.
 */
@TabwebDsl
class AvatarListScope internal constructor() : TabwebComponentScope {
  internal val avatars = mutableListOf<Avatar>()

  /**
   * Adds an already configured [Avatar] to this list.
   *
   * @param avatar the component instance to add in display order.
   * @return `Unit` after [avatar] has been added to this list.
   */
  fun avatar(avatar: Avatar) {
    avatars += avatar
  }

  operator fun plus(avatar: Avatar) {
    avatars += avatar
  }

  /**
   * Adds an icon avatar to this list.
   *
   * @param content the icon shown by the avatar.
   * @param size the avatar size.
   * @param color the background color applied behind the icon.
   * @param style the avatar shape and presentation style.
   * @return `Unit` after the configured avatar has been added to this list.
   */
  fun avatar(
    content: Icon,
    size: AvatarSize = AvatarSize.DEFAULT,
    color: BackgroundColor = BackgroundColor.DEFAULT,
    style: AvatarStyle = AvatarStyle.DEFAULT,
  ) {
    avatar(Avatar(content = content, size = size, color = color, style = style))
  }

  /**
   * Adds an initials avatar to this list.
   *
   * @param content the initials shown by the avatar.
   * @param size the avatar size.
   * @param color the background color applied behind the initials.
   * @param style the avatar shape and presentation style.
   * @return `Unit` after the configured avatar has been added to this list.
   */
  fun avatar(
    content: Initials,
    size: AvatarSize = AvatarSize.DEFAULT,
    color: BackgroundColor = BackgroundColor.DEFAULT,
    style: AvatarStyle = AvatarStyle.DEFAULT,
  ) {
    avatar(Avatar(content = content, size = size, color = color, style = style))
  }

  /**
   * Adds an image avatar to this list.
   *
   * @param content the resource image used as the avatar background.
   * @param size the avatar size.
   * @param style the avatar shape and presentation style.
   * @return `Unit` after the configured avatar has been added to this list.
   */
  fun avatar(
    content: Image.Resource,
    size: AvatarSize = AvatarSize.DEFAULT,
    style: AvatarStyle = AvatarStyle.DEFAULT,
  ) {
    avatar(Avatar(content = content, size = size, style = style))
  }

  /**
   * Adds an avatar whose content is selected by the nested [AvatarScope].
   *
   * @param size the avatar size.
   * @param color the background color applied behind icon and initials content.
   * @param style the avatar shape and presentation style.
   * @param content the DSL block that selects exactly one avatar content type.
   * @return `Unit` after the configured avatar has been added to this list.
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
