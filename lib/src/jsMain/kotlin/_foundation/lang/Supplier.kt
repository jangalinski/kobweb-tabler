package com.github.jangalinski.kobweb.tabler._foundation.lang

/**
 * A functional interface that represents a supplier of results.
 *
 * This interface is used to provide a way to generate or supply values on demand.
 *
 * @param T the type of results supplied by this supplier
 */
fun interface Supplier<T> {
  fun get(): T
}
