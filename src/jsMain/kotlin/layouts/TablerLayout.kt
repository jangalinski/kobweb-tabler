package net.janhoo.kotlin.kobweb.tabler.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout

@Layout
@Composable
fun TablerLayout(ctx: PageContext, content: @Composable () -> Unit) {
  content.invoke()
}
