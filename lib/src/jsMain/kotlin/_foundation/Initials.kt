package com.github.jangalinski.kobweb.tabler._foundation

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.varabyte.kobweb.compose.ui.Modifier


/**
 * A value class representing initials, which are typically used to represent a person's name in a shortened form.
 *
 * @property value The string value of the initials. Must be at most 3 characters long.
 * @throws IllegalArgumentException if the value is longer than 3 characters.
 */
value class Initials(private val value: String) : TabwebFoundationComponent, TabwebValue<String> {
  init {
    require(value.length <= 3) { "Initials must be at most 3 characters long" }
  }

  @Composable
  override fun invoke(modifier: Modifier) {
    KText(value = value)
  }

  override fun get() = value
}
