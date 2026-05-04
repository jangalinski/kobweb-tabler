package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun TablerStatCard(
  title: String,
  value: String,
  note: String? = null,
  badgeText: String? = null,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.classNames("col-sm-6", "col-lg-3")) {
    TablerCard(
      title = title,
      modifier = Modifier.classNames("h-100"),
    ) {
      H1(attrs = { attr("class", "h1 mb-2") }) {
        Text(value)
      }

      note?.let {
        P(attrs = { attr("class", "text-secondary m-0") }) {
          Text(it)
        }
      }

      badgeText?.let {
        Box(modifier = Modifier.classNames("mt-3")) {
          Span(attrs = { attr("class", "badge") }) {
            Text(it)
          }
        }
      }
    }
  }
}
