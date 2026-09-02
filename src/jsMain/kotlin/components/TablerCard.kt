package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text

/**
 * Renders a standard Tabler card with an optional title.
 */
@Composable
fun TablerCard(
  title: String? = null,
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit,
) {
  Column(modifier = modifier.then(ClassNames.card.modifier())) {
    title?.let {
      Column(modifier = ClassNames.cardHeader.modifier()) {
        H3(attrs = { attr("class", ClassNames.cardTitle) }) {
          Text(it)
        }
      }
    }

    Column(
      modifier = ClassNames.cardBody.modifier(),
      content = content,
    )
  }
}
