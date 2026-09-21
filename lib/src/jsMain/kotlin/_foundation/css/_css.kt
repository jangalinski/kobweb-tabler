package com.github.jangalinski.kobweb.tabler._foundation.css

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

fun cssClass(name: String) : CssClass = LazyClassNameModifier(name)

/**
 * A marker interface for modifiers that represent a CSS class name.
 */
interface CssClass : Modifier

/**
 * A marker interface for modifiers that represent a CSS size, such as `width`, `height`, `max-width`, etc.
 */
interface CssSize : CssClass

/**
 * A marker interface for modifiers that represent a CSS color, such as `color`, `background-color`, etc.
 */
interface CssColor : CssClass

/**
 * A marker interface for modifiers that represent a CSS direction, such as `left`, `right`, `top`, `bottom`, etc.
 */
interface CssDirection : CssClass


open class LazyClassNameModifier(val name: String, private val fn: (String) -> List<String?> = { listOf(it) }) : CssClass {
  private val modifier: Modifier by lazy {
    val names = fn(name).filterNotNull()
    if (names.isNotEmpty()) Modifier.classNames(names) else Modifier
  }

  override fun <R> fold(initial: R, operation: (R, Modifier.Element) -> R): R = modifier.fold(initial, operation)

  override fun then(other: Modifier): Modifier = modifier.then(other)
}
