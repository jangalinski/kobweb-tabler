package com.github.jangalinski.kobweb.tabler._foundation.css

import com.github.jangalinski.kobweb.tabler._foundation.css.CssClass
import com.github.jangalinski.kobweb.tabler._foundation.css.LazyClassNameModifier
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * Combines this modifier with [other] in declaration order.
 *
 * @param other modifier appended after this modifier.
 * @return the combined modifier.
 */
operator fun Modifier.plus(other: Modifier): Modifier = then(other)

enum class BaseCss(val value: String, delegate: LazyClassNameModifier = LazyClassNameModifier(value)) : CssClass by delegate {

  /**
   * Standard wide content container used across the layout.
   */
  CONTAINER_XL("container-xl"),

  PRINT_NONE("d-print-none"),

  ;
}
