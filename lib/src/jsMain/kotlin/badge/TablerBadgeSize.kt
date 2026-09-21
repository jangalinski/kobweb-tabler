package com.github.jangalinski.kobweb.tabler.badge

import com.github.jangalinski.kobweb.tabler._foundation.css.CssSize
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

enum class TablerBadgeSize(val value: String) : CssSize {
  SMALL("sm"),
  MEDIUM(""),
  LARGE("lg"),
  ;

  private val modifier: Modifier by lazy {
    value.takeIf { it.isNotEmpty() }?.let { "$BADGE-$it" }?.let { Modifier.classNames(it) } ?: Modifier.Companion
  }

  override fun <R> fold(initial: R, operation: (R, Modifier.Element) -> R): R {
    return modifier.fold(initial, operation)
  }

  override fun then(other: Modifier): Modifier =  modifier.then(other)
}
