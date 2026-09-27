package com.github.jangalinski.kobweb.tabler._foundation.model

import com.github.jangalinski.kobweb.tabler._foundation.lang.Supplier

value class Initials(private val value: String) : Supplier<String> {
  init {
    require(value.length <= 3) { "Initials must be at most 3 characters long" }
  }

  override fun get() = value
}
