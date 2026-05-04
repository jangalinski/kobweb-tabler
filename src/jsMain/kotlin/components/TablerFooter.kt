package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

@Composable
fun TablerFooter(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Box(modifier = modifier.classNames("footer", "footer-transparent", "d-print-none")) {
    Column(modifier = Modifier.classNames("container-xl")) {
      content()
    }
  }
}
