package io.github.jangalinski.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import io.github.jangalinski.kotlin.kobweb.tabler.models.BreadcrumbItem
import io.github.jangalinski.kotlin.kobweb.tabler.styles.ClassNames
import io.github.jangalinski.kotlin.kobweb.tabler.styles.ClassNames.modifier
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
  breadcrumbs: List<BreadcrumbItem> = emptyList(),
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.then(ClassNames.pageHeader.modifier())) {
    Column(modifier = ClassNames.containerXl.modifier()) {
      if (breadcrumbs.isNotEmpty()) {
        TablerBreadcrumbs(
          items = breadcrumbs,
          modifier = Modifier.then(ClassNames.mb2.modifier()),
        )
      }
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
