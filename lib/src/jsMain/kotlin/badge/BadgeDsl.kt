package com.github.jangalinski.kobweb.tabler.badge

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Link
import com.github.jangalinski.kobweb.tabler._foundation.TabwebComponentDsl
import com.github.jangalinski.kobweb.tabler._foundation.TabwebDsl
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * The badge DSL implementation delegated through [com.github.jangalinski.kobweb.tabler.KobwebTabler].
 */
data object BadgeDsl : TabwebComponentDsl, BadgeComposable {
  @Composable
  override fun badge(
    text: String,
    color: BackgroundColor,
    style: BadgeStyle,
    size: BadgeSize,
    shape: BadgeShape,
    link: Link?,
    modifier: Modifier,
  ) {
    Badge(text = text, color = color, style = style, size = size, shape = shape, link = link)(modifier)
  }

  @Composable
  override fun badge(
    text: String,
    icon: Icon,
    iconPosition: BadgeIconPosition,
    color: BackgroundColor,
    style: BadgeStyle,
    size: BadgeSize,
    shape: BadgeShape,
    link: Link?,
    modifier: Modifier,
  ) {
    Badge(text = text, icon = icon, iconPosition = iconPosition, color = color, style = style, size = size, shape = shape, link = link)(modifier)
  }

  @Composable
  override fun badge(
    icon: Icon,
    color: BackgroundColor,
    style: BadgeStyle,
    size: BadgeSize,
    shape: BadgeShape,
    link: Link?,
    modifier: Modifier,
  ) {
    Badge(icon = icon, color = color, style = style, size = size, shape = shape, link = link)(modifier)
  }

  @Composable
  override fun badges(modifier: Modifier, content: BadgeListScope.() -> Unit) {
    BadgeList(badges = BadgeListScope().apply(content).badges)(modifier)
  }
}
