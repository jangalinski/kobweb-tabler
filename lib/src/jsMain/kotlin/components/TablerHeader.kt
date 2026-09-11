package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.github.jangalinski.kobweb.tabler.models.BreadcrumbItem
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
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
  org.jetbrains.compose.web.dom.Div(
    attrs = modifier.then(ClassNames.pageHeader.modifier()).toAttrs(),
  ) {
    org.jetbrains.compose.web.dom.Div(attrs = ClassNames.containerXl.modifier().toAttrs()) {
      if (breadcrumbs.isNotEmpty()) {
        TablerBreadcrumbs(
          items = breadcrumbs,
          modifier = Modifier.then(ClassNames.mb2.modifier()),
        )
      }
      org.jetbrains.compose.web.dom.Div(
        attrs = ClassNames.row.modifier()
          .then(ClassNames.g2.modifier())
          .then(ClassNames.alignItemsCenter.modifier())
          .toAttrs(),
      ) {
        org.jetbrains.compose.web.dom.Div(attrs = ClassNames.pageHeaderCol.modifier().toAttrs()) {
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
