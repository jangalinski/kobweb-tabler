package com.github.jangalinski.kobweb.tabler._foundation

import com.github.jangalinski.kobweb.tabler._foundation.lang.Supplier

/**
 * A value class representing initials, which are typically used to represent a person's name in a shortened form.
 *
 * @property value The string value of the initials. Must be at most 3 characters long.
 * @throws IllegalArgumentException if the value is longer than 3 characters.
 */
value class Initials(private val value: String) : Supplier<String> {
  init {
    require(value.length <= 3) { "Initials must be at most 3 characters long" }
  }

  override fun get() = value
}
