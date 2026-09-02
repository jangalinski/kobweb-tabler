@file:Layout("com.github.jangalinski.kobweb.tabler.layouts.TablerLayout")

package com.github.jangalinski.kobweb.tabler.example.tagessieg.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.github.jangalinski.kobweb.tabler.example.tagessieg.SiteRoutes
import com.github.jangalinski.kobweb.tabler.example.tagessieg.siteBreadcrumbs
import com.github.jangalinski.kobweb.tabler.example.tagessieg.siteLayoutData
import com.github.jangalinski.kobweb.tabler.example.tagessieg.sitePageMeta
import com.github.jangalinski.kobweb.tabler.components.TablerAvatar
import com.github.jangalinski.kobweb.tabler.components.TablerCards
import com.github.jangalinski.kobweb.tabler.components.TablerTracking
import com.github.jangalinski.kobweb.tabler.components.TrackingBlock
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarColor
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarContent
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarData
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarShape
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarSize
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarStatus
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarStatusColor
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler.styles.GridWidth
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.HALF
import com.github.jangalinski.kobweb.tabler.styles.GridWidth.QUARTER
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

/**
 * Registers the home page metadata and shared layout data before the page renders.
 */
@InitRoute
fun initIndexPage(ctx: InitRouteContext) {
  ctx.data.add(sitePageMeta(title = "Home", subtitle = "Tagessieg", breadcrumbs = siteBreadcrumbs(SiteRoutes.Root)))
  ctx.data.add(siteLayoutData(activeRoute = SiteRoutes.Root))
}

/**
 * Renders the home page content.
 */
@Page
@Composable
fun Index() {
  TablerCards {
    statCard(
      title = "Home",
      value = "Tagessieg",
      note = "Brand click target and landing page.",
      width = QUARTER,
    )
    card(title = "Welcome", width = QUARTER) {
      P {
        Text("This is the home page.")
      }
      P {
        Text("Use Analysis for the analysis page.")
      }
    }
    card(title = "Avatar profiles", width = HALF) {
      P(attrs = { attr("class", ClassNames.textSecondaryM0) }) {
        Text("Image, initials, and icon avatars with Tabler variants")
      }
      Div(attrs = { attr("class", "d-flex align-items-center gap-3 mt-3") }) {
        TablerAvatar(
          TablerAvatarData(
            content = TablerAvatarContent.ImageResource("jan-g-avatar.png", altText = "Jan Galinski"),
            size = TablerAvatarSize.LARGE,
            shape = TablerAvatarShape.CIRCLE,
            status = TablerAvatarStatus(TablerAvatarStatusColor.SUCCESS),
            ariaLabel = "Jan Galinski, online",
          ),
        )
        TablerAvatar(
          TablerAvatarData(
            content = TablerAvatarContent.Initials("JG"),
            color = TablerAvatarColor.PURPLE,
            shape = TablerAvatarShape.LARGE_ROUNDED,
            status = TablerAvatarStatus(TablerAvatarStatusColor.WARNING, label = "2"),
            ariaLabel = "Jan Galinski, two notifications",
          ),
        )
        TablerAvatar(
          TablerAvatarData(
            content = TablerAvatarContent.Icon(
              svg = USER_ICON_SVG,
              altText = "User profile",
            ),
            color = TablerAvatarColor.AZURE,
            size = TablerAvatarSize.SMALL,
            shape = TablerAvatarShape.SQUARE,
            status = TablerAvatarStatus(TablerAvatarStatusColor.INFO),
            ariaLabel = "User profile",
          ),
        )
        TablerAvatar(
          TablerAvatarData(
            content = TablerAvatarContent.ImageResource("jan-g-avatar.png", altText = "Jan Galinski"),
            size = TablerAvatarSize.EXTRA_LARGE,
            shape = TablerAvatarShape.LARGE_ROUNDED,
            ariaLabel = "Jan Galinski, online",
          ),
        )
      }
    }
    card(title = "Status monitoring", width = GridWidth.THIRD) {
      P(attrs = { attr("class", ClassNames.textSecondaryM0) }) {
        Text("Recent Tagessieg data refreshes")
      }
      TablerTracking(
        blocks = listOf(
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "High source load", variantClass = "bg-warning"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Source unavailable", variantClass = "bg-danger"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "No refresh data"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "High source load", variantClass = "bg-warning"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
          TrackingBlock(tooltip = "Refresh completed", variantClass = "bg-success"),
        ),
        modifier = ClassNames.mt2.modifier(),
      )
    }
  }
}

private const val USER_ICON_SVG = """
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
    <path d="M8 7a4 4 0 1 0 8 0a4 4 0 0 0 -8 0" />
    <path d="M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
  </svg>
"""
