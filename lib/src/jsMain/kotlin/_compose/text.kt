package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Text

/** Internal DOM adapter for text content. */
@Composable
fun KText(value: String) {
    Text(value)
}
