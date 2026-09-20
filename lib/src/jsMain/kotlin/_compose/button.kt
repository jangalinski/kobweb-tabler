package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Button

/**
 * Internal DOM adapter for a button with Kobweb modifier support.
 */
@Composable
fun KButton(modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
    Button(attrs = modifier.toAttrs()) {
        content()
    }
}
