package com.github.jangalinski.kobweb.tabler._foundation

fun url(value: String): Url = when {
  value.startsWith("http") -> ExternalUrl(value)
  value.startsWith("/") -> PublicUrl(value)
  else -> throw IllegalArgumentException("Url must start with http or /")
}

sealed interface Url {
  val value: String
}

value class ExternalUrl(override val value: String) : Url {
  init {
    require(value.startsWith("http")) { "External Url must start with http" }
  }
}

value class PublicUrl(override val value: String) : Url {
  init {
    require(value.startsWith("/")) { "Public Url must start with /" }
  }
}

val HOME = PublicUrl("/")
