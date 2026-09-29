package com.github.jangalinski.tabweb._foundation

import com.varabyte.kobweb.compose.css.BackgroundImage
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.attr
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage


internal const val ATTR_ARIA_LABEL = "aria-label"

/**
 * Combines this modifier with [other] in declaration order.
 *
 * @param other modifier appended after this modifier.
 * @return the combined modifier.
 */
operator fun Modifier.plus(other: Modifier): Modifier = then(other)

fun Modifier.takeIf(condition: Boolean) = if (condition) this else Modifier.Companion

fun Modifier.ariaLabel(label: String? = null) = label?.let { this.attr(ATTR_ARIA_LABEL, it) } ?: this

val BackgroundImage.modifier: Modifier get() =  Modifier.backgroundImage(this)
