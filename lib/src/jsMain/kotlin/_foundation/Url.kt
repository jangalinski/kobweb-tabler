package com.github.jangalinski.kobweb.tabler._foundation

import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.compose.css.functions.url as cssUrl

sealed interface Url : Tabler.Supplier<String> {
  companion object {
    operator fun invoke(value: String): Url = when {
      value.startsWith("http") -> External(value)
      value.startsWith("/") -> Internal(value)
      else -> throw IllegalArgumentException("Url must start with http or /")
    }
  }

  /**
   * Returns a CSS url() function string for this Url.
   *
   * @return A string in the format of url(<url>), where <url> is the value of this Url.
   */
  fun cssUrl() = cssUrl(get())

  /**
   * A value class representing an external URL, which must start with "http".
   */
  value class External(val value: String) : Url {
    init {
      require(value.startsWith("http")) { "External Url must start with http" }
    }

    override fun get() = value
  }

  /**
   * A value class representing an internal URL, which must start with "/".
   */
  value class Internal(val value: String) : Url {
    init {
      require(value.startsWith("/")) { "Internal Url must start with /" }
    }

    override fun get(): String = BasePath.prependTo(value)
  }
}
