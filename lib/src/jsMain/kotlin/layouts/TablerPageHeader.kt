package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerBreadcrumbs
import com.github.jangalinski.kobweb.tabler.models.BreadcrumbItem
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

/**
 * Renders the main Tabler page header block.
 * Page-header block for the current Tabler route.
 *
 * This corresponds to the preview's `<!-- BEGIN PAGE HEADER -->` region. It
 * is layout chrome owned by the shared Kobweb layout, while title, subtitle,
 * breadcrumbs, and header actions are route-level data or content.
 * Kobweb layouts use it for route metadata that should remain outside the
 * route's main content, such as the title, subtitle, breadcrumbs, and future
 * header actions.
 *
 * @param title visible page title.
 * @param subtitle optional supporting text below the title.
 * @param breadcrumbs optional breadcrumb trail rendered above the title.
 * @param modifier additional attributes or classes applied to the header block.
 */
@Composable
fun TablerPageHeader(
  title: String,
  subtitle: String? = null,
  breadcrumbs: List<BreadcrumbItem> = emptyList(),
  modifier: Modifier = Modifier,
) {
  Div(
    attrs = modifier.then(ClassNames.pageHeader.modifier()).toAttrs(),
  ) {
    Div(attrs = ClassNames.containerXl.modifier().toAttrs()) {
      if (breadcrumbs.isNotEmpty()) {
        TablerBreadcrumbs(
          items = breadcrumbs,
          modifier = Modifier.then(ClassNames.mb2.modifier()),
        )
      }
      Div(
        attrs = ClassNames.row.modifier()
          .then(ClassNames.g2.modifier())
          .then(ClassNames.alignItemsCenter.modifier())
          .toAttrs(),
      ) {
        Div(attrs = ClassNames.pageHeaderCol.modifier().toAttrs()) {
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
