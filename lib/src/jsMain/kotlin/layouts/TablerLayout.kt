package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.LocalTablerSiteConfig
import com.github.jangalinski.kobweb.tabler.components.TablerFooter
import com.github.jangalinski.kobweb.tabler.components.render
import com.github.jangalinski.kobweb.tabler.models.TablerLayoutData
import com.github.jangalinski.kobweb.tabler.models.TablerPageMeta
import com.github.jangalinski.kobweb.tabler.navbar.TablerNavbar
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.Div

/**
 * Kobweb layout that renders the shared Tabler page shell around a route.
 *
 * The composition follows the preview's major regions: `BEGIN SIDEBAR` or
 * `BEGIN NAVBAR`, then `page-wrapper`, `BEGIN PAGE HEADER`, `BEGIN PAGE BODY`,
 * and `BEGIN FOOTER`. The route itself is supplied through [content], while
 * shared navigation, footer content, and page metadata arrive through
 * `@InitRoute` data.
 *
 * Keeping this shell in a Kobweb `@Layout` lets pages focus on their own
 * content while preserving one stable DOM boundary for Tabler CSS and
 * responsive behavior.
 *
 * ```
 * TablerPage
 *  ├─ TablerSidebar and TablerNavbar
 *  ├─ TablerPageWrapper
 *  │  ├─ TablerPageHeader
 *  │  ├─ PagePart (TablerPageBody)
 *  │  └─ TablerFooter
 * ```
 *
 * @param ctx Kobweb page context containing route-scoped layout data.
 * @param content route content rendered in the page body.
 */
@Layout
@Composable
fun TablerLayout(
  ctx: PageContext,
  content: @Composable () -> Unit
) {
  val site = LocalTablerSiteConfig.current
  val layoutData = ctx.data.getValue<TablerLayoutData>()
  val pageMeta = ctx.data.getValue<TablerPageMeta>()
  val footer = layoutData.footer ?: site.shell.footer

  TablerPage {

    TablerNavbar(
      brand = site.shell.brand,
      data = site.shell.navbar.create(layoutData.activeRoute),
      actions = site.shell.navbarActions,
    )

//    if (layoutData.sidebar == null && layoutData.navbar == null) {
//      navigation.render()
//    } else {
//      layoutData.sidebar?.let { TablerSidebar(it) }
//      layoutData.navbar?.let { TablerNavbar(it) }
//    }

    TablerPageWrapper {

      TablerPageHeader(
        title = pageMeta.title,
        subtitle = pageMeta.subtitle,
        breadcrumbs = pageMeta.breadcrumbs,
      )

      TablerPageBody {
        content()
      }

      TablerFooter {
        footer()
      }
    }
  }
}

/**
 * Root container for a Tabler page shell.
 *
 * This corresponds to the preview's outer `<!-- BEGIN PAGE -->` region and
 * normally contains the sidebar, navbar, and [TablerPageWrapper]. In Kobweb
 * terms, it is the stable composable boundary around route content rather
 * than a page route itself.
 */
@Composable
private fun TablerPage(block: @Composable () -> Unit) {
  Div(attrs = ClassNames.page.modifier().toAttrs()) {
    block()
  }
}

/**
 * Wrapper for the route-facing portion of a Tabler page.
 *
 * This is the preview's `.page-wrapper` block. It groups the page header,
 * [TablerPageBody], and footer while the outer [TablerPage] keeps global
 * navigation beside it. That separation mirrors Kobweb's layout design:
 * shared chrome surrounds the composable content supplied by a route.
 */
@Composable
private fun TablerPageWrapper(block: @Composable () -> Unit) {
  Div(attrs = ClassNames.pageWrapper.modifier().toAttrs()) {
    block()
  }
}
