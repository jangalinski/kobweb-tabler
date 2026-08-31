package io.github.jangalinski.kotlin.kobweb.tabler.example.tagessieg

import kotlin.js.Date

/**
 * Formats the current browser time in the German `dd.MM.yyyy HH:mm` style.
 *
 * The timestamp is used in the shared navbar so the page shows when it was generated.
 */
fun germanDateTime(): String {
  val now = Date()
  return buildString {
    append(now.getDate().toString().padStart(2, '0'))
    append('.')
    append((now.getMonth() + 1).toString().padStart(2, '0'))
    append('.')
    append(now.getFullYear().toString())
    append(' ')
    append(now.getHours().toString().padStart(2, '0'))
    append(':')
    append(now.getMinutes().toString().padStart(2, '0'))
  }
}
