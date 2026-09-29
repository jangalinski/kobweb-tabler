package com.github.jangalinski.kobweb.tabler.badge

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * A Tabler wrapper that spaces [Badge]s inline.
 */
interface BadgeList : Tabler.Component {
  companion object {
    /**
     * Creates a list containing the supplied badges.
     *
     * @param badges badges in display order.
     * @return a [BadgeList] containing [badges].
     */
    operator fun invoke(vararg badges: Badge): BadgeList = invoke(badges.toList())

    /**
     * Creates a list containing the supplied badges.
     *
     * @param badges badges in display order.
     * @return a [BadgeList] containing [badges].
     */
    operator fun invoke(badges: List<Badge>): BadgeList = object : BadgeList {
      override val badges = badges
    }
  }

  /**
   * Badges rendered in display order.
   */
  val badges: List<Badge>

  @Composable
  override fun invoke(modifier: Modifier) {
    KDiv(modifier = BadgeCss.badgeList + modifier) {
      badges.forEach { it() }
    }
  }
}
