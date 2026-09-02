package com.github.jangalinski.kobweb.tabler.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Modifier
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

  Box(modifier = ClassNames.page.modifier()) {
    layoutData.navigation.render()

    Column(modifier = ClassNames.pageWrapper.modifier()) {
      PagePart(ClassNames.pageHeader.modifier()) {
        TablerHeader(
          title = pageMeta.title,
          subtitle = pageMeta.subtitle,
          breadcrumbs = pageMeta.breadcrumbs,
        )
      }

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
  block: @Composable ColumnScope.() -> Unit,
) {
  Column(modifier = modifier) {
    Column(modifier = ClassNames.containerXl.modifier()) {
      block()
    }
  }
}
