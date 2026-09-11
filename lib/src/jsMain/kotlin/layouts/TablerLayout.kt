package com.github.jangalinski.kobweb.tabler.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import com.github.jangalinski.kobweb.tabler.components.TablerFooter
import com.github.jangalinski.kobweb.tabler.components.TablerHeader
import com.github.jangalinski.kobweb.tabler.components.render
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler.models.TablerLayoutData
import com.github.jangalinski.kobweb.tabler.models.TablerPageMeta

/**
 * Renders the shared Tabler shell around a page body.
 *
 * The layout reads page metadata from `@InitRoute` data so pages only need to provide
 * title/subtitle/navigation state before their own content renders.
 */
@Layout
@Composable
fun TablerLayout(ctx: PageContext, content: @Composable () -> Unit) {
  val layoutData = ctx.data.getValue<TablerLayoutData>()
  val pageMeta = ctx.data.getValue<TablerPageMeta>()

  org.jetbrains.compose.web.dom.Div(attrs = ClassNames.page.modifier().toAttrs()) {
    layoutData.navigation.render()

    org.jetbrains.compose.web.dom.Div(attrs = ClassNames.pageWrapper.modifier().toAttrs()) {
      TablerHeader(
        title = pageMeta.title,
        subtitle = pageMeta.subtitle,
        breadcrumbs = pageMeta.breadcrumbs,
      )

      PagePart(ClassNames.pageBody.modifier()) {
        content()
      }

      TablerFooter {
        layoutData.footer(this)
      }
    }
  }
}

@Composable
private fun PagePart(
  modifier: Modifier,
  block: @Composable () -> Unit,
) {
  org.jetbrains.compose.web.dom.Div(attrs = modifier.toAttrs()) {
    org.jetbrains.compose.web.dom.Div(attrs = ClassNames.containerXl.modifier().toAttrs()) {
      block()
    }
  }
}
