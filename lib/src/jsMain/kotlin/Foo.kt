package com.github.jangalinski.kobweb.tabler

fun interface ToString {
  fun hello(): String
}

@Deprecated(
  message = "This class is deprecated and will be removed in future versions.",
  replaceWith = ReplaceWith("Foo()")
)
class Foo : ToString {
  override fun hello(): String {
    return "Hello, World!"
  }
}
