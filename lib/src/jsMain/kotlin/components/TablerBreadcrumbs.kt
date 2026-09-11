package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.models.BreadcrumbItem
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.varabyte.kobweb.navigation.Anchor
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.navigation.remove
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Ol
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/**
 * Renders a Tabler-style breadcrumb trail.
 *
 * The links intentionally stay visually quiet so they blend into the shell without
 * looking like default browser links.
 */
@Composable
fun TablerBreadcrumbs(
  items: List<BreadcrumbItem>,
  modifier: Modifier = Modifier,
) {
  if (items.isEmpty()) return

  org.jetbrains.compose.web.dom.Div(attrs = modifier.toAttrs()) {
    Nav(attrs = {
      attr("aria-label", "breadcrumb")
    }) {
      Ol(attrs = { attr("class", ClassNames.breadcrumb) }) {
        items.forEachIndexed { index, item ->
          val isLast = index == items.lastIndex
          Li(attrs = {
            attr(
              "class",
              if (isLast || item.active) {
                "${ClassNames.breadcrumbItem} ${ClassNames.breadcrumbItemActive}"
              } else {
                ClassNames.breadcrumbItem
              },
            )
          }) {
            when {
              !isLast && !item.active && !item.href.isNullOrBlank() -> Anchor(
                href = BasePath.remove(item.href),
                attrs = {
                  attr("class", ClassNames.breadcrumbLink)
                },
              ) {
                Text(item.label)
              }
              else -> Span(attrs = {
                if (isLast || item.active) {
                  attr("aria-current", "page")
                }
              }) {
                Text(item.label)
              }
            }
          }
        }
      }
    }
  }
}
