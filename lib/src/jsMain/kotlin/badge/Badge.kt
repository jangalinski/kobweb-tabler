package com.github.jangalinski.kobweb.tabler.badge

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.github.jangalinski.kobweb.tabler._foundation.compose.KSpan
import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * A small Tabler label used to show a status, count, or tag.
 */
interface Badge : Tabler.Component {
  companion object {
    /**
     * Creates a configured [Badge].
     *
     * @param text label shown by the badge.
     * @param color background color for a solid or light badge, and the border color for an outline badge.
     * @param style visual treatment for the badge.
     * @param size size of the badge.
     * @return a configured [Badge] component instance.
     */
    operator fun invoke(
      text: String,
      color: BackgroundColor = BackgroundColor.SEMANTIC.PRIMARY,
      style: BadgeStyle = BadgeStyle.DEFAULT,
      size: BadgeSize = BadgeSize.DEFAULT,
    ): Badge = object : Badge {
      override val content = BadgeContent.Text(text)
      override val color = color
      override val style = style
      override val size = size
    }

    /**
     * Creates a label [Badge] with an icon at either edge.
     *
     * @param text label shown by the badge.
     * @param icon icon shown beside [text].
     * @param iconPosition edge at which [icon] is rendered.
     * @param color background color for a solid or light badge, and the border color for an outline badge.
     * @param style visual treatment for the badge.
     * @param size size of the badge.
     * @return a configured [Badge] component instance.
     */
    operator fun invoke(
      text: String,
      icon: Icon,
      iconPosition: BadgeIconPosition = BadgeIconPosition.LEFT,
      color: BackgroundColor = BackgroundColor.SEMANTIC.PRIMARY,
      style: BadgeStyle = BadgeStyle.DEFAULT,
      size: BadgeSize = BadgeSize.DEFAULT,
    ): Badge = object : Badge {
      override val content = BadgeContent.TextWithIcon(text, icon, iconPosition)
      override val color = color
      override val style = style
      override val size = size
    }

    /**
     * Creates an icon-only [Badge].
     *
     * @param icon icon shown by the badge.
     * @param color background color for a solid or light badge, and the border color for an outline badge.
     * @param style visual treatment for the badge.
     * @param size size of the badge.
     * @return a configured [Badge] component instance.
     */
    operator fun invoke(
      icon: Icon,
      color: BackgroundColor = BackgroundColor.SEMANTIC.PRIMARY,
      style: BadgeStyle = BadgeStyle.DEFAULT,
      size: BadgeSize = BadgeSize.DEFAULT,
    ): Badge = object : Badge {
      override val content = BadgeContent.IconOnly(icon)
      override val color = color
      override val style = style
      override val size = size
    }
  }

  /**
   * Typed text and icon content rendered by the badge.
   */
  val content: BadgeContent

  /**
   * Color used by the badge treatment.
   */
  val color: BackgroundColor

  /**
   * Visual treatment applied to the badge.
   */
  val style: BadgeStyle

  /**
   * Size applied to the badge.
   */
  val size: BadgeSize

  @Composable
  override fun invoke(modifier: Modifier) {
    val modifiers = BadgeCss.badge + style.toBadgeModifier(color) + size.toBadgeModifier() + modifier

    when (val content = content) {
      is BadgeContent.Text -> KSpan(modifier = modifiers, text = content.value)
      is BadgeContent.TextWithIcon -> KSpan(modifier = modifiers) {
        if (content.position == BadgeIconPosition.LEFT) content.icon()
        KSpan(text = content.text)
        if (content.position == BadgeIconPosition.RIGHT) content.icon()
      }
      is BadgeContent.IconOnly -> KSpan(modifier = modifiers + BadgeCss.iconOnly) {
        content.icon()
      }
    }
  }
}
