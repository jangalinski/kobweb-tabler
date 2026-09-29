package com.github.jangalinski.kobweb.tabler.avatar

import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

/**
 * Enumerates the supported Tabler shape and presentation styles for an [Avatar].
 */
enum class AvatarStyle(private val value: String) : Tabler.Style, Tabler.Supplier<String> {
  CIRCLE("avatar-circle"),
  ROUNDED("avatar-rounded"),
  SQUARE("avatar-square"),
  UPLOAD("avatar-upload"),
  ;

  companion object {
    val DEFAULT = CIRCLE
  }

  private val modifier: Modifier by lazy {
    Modifier.classNames(value)
  }

  override fun <R> fold(initial: R, operation: (R, Modifier.Element) -> R): R = modifier.fold(initial, operation)

  override fun then(other: Modifier): Modifier = modifier.then(other)

  override fun get() = value
}
