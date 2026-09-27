package com.github.jangalinski.kobweb.tabler._foundation.resource

import com.github.jangalinski.kobweb.tabler._foundation.lang.Supplier
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.compose.css.functions.url as cssUrl

fun url(value: String): Url = when {
  value.startsWith("http") -> ExternalUrl(value)
  value.startsWith("/") -> InternalUrl(value)
  else -> throw IllegalArgumentException("Url must start with http or /")
}

sealed interface Url : Supplier<String> {
  fun cssUrl() = cssUrl(get())
}

value class ExternalUrl(val value: String) : Url {
  init {
    require(value.startsWith("http")) { "External Url must start with http" }
  }

  override fun get() = value
}

value class InternalUrl(val value: String) : Url {
  init {
    require(value.startsWith("/")) { "Internal Url must start with /" }
  }

  override fun get(): String = BasePath.prependTo(value)
}

val HOME = InternalUrl("/")
