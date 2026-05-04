package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text

@Composable
fun TablerCard(
  title: String? = null,
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit,
) {
  Column(modifier = modifier.classNames("card")) {
    title?.let {
      Column(modifier = Modifier.classNames("card-header")) {
        H3(attrs = { attr("class", "card-title") }) {
          Text(it)
        }
      }
    }

    Column(
      modifier = Modifier.classNames("card-body"),
      content = content,
    )
  }
}
