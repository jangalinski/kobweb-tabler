package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.modifier
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

/**
 * Renders the main Tabler page header block.
 */
@Composable
fun TablerHeader(
  title: String,
  subtitle: String? = null,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.then(ClassNames.pageHeader.modifier())) {
    Column(modifier = ClassNames.containerXl.modifier()) {
      Row(
        modifier = ClassNames.row.modifier()
          .then(ClassNames.g2.modifier())
          .then(ClassNames.alignItemsCenter.modifier()),
      ) {
        Column(modifier = ClassNames.pageHeaderCol.modifier()) {
          H1(attrs = { attr("class", ClassNames.pageTitle) }) {
            Text(title)
          }
          subtitle?.takeIf { it.isNotBlank() }?.let {
            P(attrs = { attr("class", ClassNames.textSecondarySubheader) }) {
              Text(it)
            }
          }
        }
      }
    }
  }
}
