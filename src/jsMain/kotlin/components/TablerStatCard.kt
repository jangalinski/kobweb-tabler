package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.modifier
import net.janhoo.kotlin.kobweb.tabler.styles.GridWidth
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/**
 * Renders a compact summary card for tabular or numeric statistics.
 */
@Composable
fun TablerStatCard(
  title: String,
  value: String,
  note: String? = null,
  badgeText: String? = null,
  width: GridWidth = GridWidth.QUARTER,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.then(width.classNames.modifier())) {
    TablerCard(
      title = title,
      modifier = ClassNames.h100.modifier(),
    ) {
      H1(attrs = { attr("class", ClassNames.h1Mb2) }) {
        Text(value)
      }

      note?.let {
        P(attrs = { attr("class", ClassNames.textSecondaryM0) }) {
          Text(it)
        }
      }

      badgeText?.let {
        Box(modifier = ClassNames.mt3.modifier()) {
          Span(attrs = { attr("class", ClassNames.badge) }) {
            Text(it)
          }
        }
      }
    }
  }
}
