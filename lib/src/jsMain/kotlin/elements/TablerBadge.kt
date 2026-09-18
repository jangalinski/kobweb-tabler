package com.github.jangalinski.kobweb.tabler.elements

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.styles.CssSize
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

const val BADGE = "badge"

@Composable
fun TablerBadge() {
  TODO()
}

data class TablerBadgeData(
  val text: String,
  val size: TablerBadgeSize = TablerBadgeSize.MEDIUM,
  val color: String = "primary",
  val pill: Boolean = false,
  val outline: Boolean = false,
)

enum class TablerBadgeSize(val value: String) : CssSize {
  SMALL("sm"),
  MEDIUM(""),
  LARGE("lg"),
  ;

  private val modifier: Modifier by lazy {
    value.takeIf { it.isNotEmpty() }?.let { "$BADGE-$it" }?.let { Modifier.classNames(it) } ?: Modifier
  }

  override fun <R> fold(initial: R, operation: (R, Modifier.Element) -> R): R {
    return modifier.fold(initial, operation)
  }

  override fun then(other: Modifier): Modifier =  modifier.then(other)
}
