package com.github.jangalinski.kobweb.tabler.badge

import com.github.jangalinski.kobweb.tabler._foundation.TabwebDsl
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon

/**
 * Provides the children for a badge list DSL.
 */
@TabwebDsl
class BadgeListScope internal constructor() {
  internal val badges = mutableListOf<Badge>()

  /**
   * Adds an already configured [Badge] to this list.
   *
   * @param badge component instance to add in display order.
   * @return `Unit` after [badge] has been added to this list.
   */
  fun badge(badge: Badge) {
    badges += badge
  }

  /**
   * Creates and adds a [Badge] to this list.
   *
   * @param text label shown by the badge.
   * @param color background color for a solid or light badge, and the border color for an outline badge.
   * @param style visual treatment for the badge.
   * @param size size of the badge.
   * @return `Unit` after the configured badge has been added to this list.
   */
  fun badge(
    text: String,
    color: BackgroundColor = BackgroundColor.SEMANTIC.PRIMARY,
    style: BadgeStyle = BadgeStyle.DEFAULT,
    size: BadgeSize = BadgeSize.DEFAULT,
  ) {
    badge(Badge(text = text, color = color, style = style, size = size))
  }

  /**
   * Creates and adds a text [Badge] with an icon at either edge.
   *
   * @param text label shown by the badge.
   * @param icon icon shown beside [text].
   * @param iconPosition edge at which [icon] is rendered.
   * @param color background color for a solid or light badge, and the border color for an outline badge.
   * @param style visual treatment for the badge.
   * @param size size of the badge.
   * @return `Unit` after the configured badge has been added to this list.
   */
  fun badge(
    text: String,
    icon: Icon,
    iconPosition: BadgeIconPosition = BadgeIconPosition.LEFT,
    color: BackgroundColor = BackgroundColor.SEMANTIC.PRIMARY,
    style: BadgeStyle = BadgeStyle.DEFAULT,
    size: BadgeSize = BadgeSize.DEFAULT,
  ) {
    badge(Badge(text = text, icon = icon, iconPosition = iconPosition, color = color, style = style, size = size))
  }

  /**
   * Creates and adds an icon-only [Badge].
   *
   * @param icon icon shown by the badge.
   * @param color background color for a solid or light badge, and the border color for an outline badge.
   * @param style visual treatment for the badge.
   * @param size size of the badge.
   * @return `Unit` after the configured badge has been added to this list.
   */
  fun badge(
    icon: Icon,
    color: BackgroundColor = BackgroundColor.SEMANTIC.PRIMARY,
    style: BadgeStyle = BadgeStyle.DEFAULT,
    size: BadgeSize = BadgeSize.DEFAULT,
  ) {
    badge(Badge(icon = icon, color = color, style = style, size = size))
  }
}
