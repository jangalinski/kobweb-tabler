package com.github.jangalinski.kobweb.tabler

fun interface ToString {
  fun hello(): String
}

class Foo : ToString {
  override fun hello(): String {
    return "Hello, World!"
  }
}
