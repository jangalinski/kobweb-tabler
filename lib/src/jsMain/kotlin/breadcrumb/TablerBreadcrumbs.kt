package com.github.jangalinski.kobweb.tabler.breadcrumb

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.breadcrumb.BreadcrumbItem
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.attr
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.github.jangalinski.kobweb.tabler._foundation.css.ClassNames
import com.github.jangalinski.kobweb.tabler._foundation.css.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler._foundation.compose.KAnchor
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.compose.KLi
import com.github.jangalinski.kobweb.tabler._foundation.compose.KNav
import com.github.jangalinski.kobweb.tabler._foundation.compose.KOl
import com.github.jangalinski.kobweb.tabler._foundation.compose.KSpan
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.navigation.remove

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

  KDiv(modifier = modifier) {
    KNav(label = "breadcrumb") {
      KOl(modifier = ClassNames.breadcrumb.modifier()) {
        items.forEachIndexed { index, item ->
          val isLast = index == items.lastIndex
          val itemModifier = Modifier.classNames(
            ClassNames.breadcrumbItem,
            if (isLast || item.active) ClassNames.breadcrumbItemActive else "",
          )
          KLi(modifier = itemModifier) {
            when {
              !isLast && !item.active && !item.href.isNullOrBlank() -> KAnchor(
                href = BasePath.remove(item.href),
                modifier = ClassNames.breadcrumbLink.modifier(),
              ) {
                KText(item.label)
              }
              else -> KSpan(
                modifier = if (isLast || item.active) Modifier.attr("aria-current", "page") else Modifier,
              ) {
                KText(item.label)
              }
            }
          }
        }
      }
    }
  }
}
