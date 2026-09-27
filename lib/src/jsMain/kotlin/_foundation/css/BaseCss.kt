package com.github.jangalinski.kobweb.tabler._foundation.css

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

/**
 * Combines this modifier with [other] in declaration order.
 *
 * @param other modifier appended after this modifier.
 * @return the combined modifier.
 */
operator fun Modifier.plus(other: Modifier): Modifier = then(other)

enum class BaseCss(val value: String) : CssClass  {

  /**
   * Standard wide content container used across the layout.
   */
  CONTAINER_XL("container-xl"),

  PRINT_NONE("d-print-none"),

  ;


  private val modifier: Modifier by lazy {
    Modifier.classNames(value)
  }

  override fun <R> fold(initial: R, operation: (R, Modifier.Element) -> R): R = modifier.fold(initial, operation)

  override fun then(other: Modifier): Modifier = modifier.then(other)

}
