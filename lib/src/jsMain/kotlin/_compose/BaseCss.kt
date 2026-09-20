package com.github.jangalinski.kobweb.tabler._compose

import com.github.jangalinski.kobweb.tabler.styles.CssClass
import com.github.jangalinski.kobweb.tabler.styles.LazyClassNameModifier
import com.varabyte.kobweb.compose.ui.Modifier

operator fun Modifier.plus(other: Modifier): Modifier = then(other)

enum class BaseCss(val value: String, delegate: LazyClassNameModifier = LazyClassNameModifier(value)) : CssClass by delegate {

  /**
   * Standard wide content container used across the layout.
   */
  CONTAINER_XL("container-xl"),

  PRINT_NONE("d-print-none"),

  ;
}
