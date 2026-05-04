package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun TablerHeader(
  title: String,
  subtitle: String,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.classNames("page-header", "d-print-none")) {
    Column(modifier = Modifier.classNames("container-xl")) {
      Row(modifier = Modifier.classNames("row", "g-2", "align-items-center")) {
        Column(modifier = Modifier.classNames("col")) {
          H1(attrs = { attr("class", "page-title") }) {
            Text(title)
          }
          P(attrs = { attr("class", "text-secondary subheader") }) {
            Text(subtitle)
          }
        }
      }
    }
  }
}
