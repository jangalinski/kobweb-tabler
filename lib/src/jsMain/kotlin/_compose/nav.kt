package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Nav

/** Internal DOM adapter for a navigation landmark with Kobweb modifier support. */
@Composable
fun KNav(label: String, modifier: Modifier = Modifier.Companion, content: @Composable () -> Unit) {
    Nav(attrs = modifier.toAttrs { attr("aria-label", label) }) {
        content()
    }
}
