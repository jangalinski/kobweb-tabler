package com.github.jangalinski.kobweb.tabler.site.pages.interfaces

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.css.GridWidth
import com.github.jangalinski.kobweb.tabler._foundation.css.GridWidth.HALF
import com.github.jangalinski.kobweb.tabler._foundation.Initials
import com.github.jangalinski.kobweb.tabler._foundation.widget.MarkdownText
import com.github.jangalinski.kobweb.tabler.avatar.AvatarList
import com.github.jangalinski.kobweb.tabler.avatar.IconAvatar
import com.github.jangalinski.kobweb.tabler.avatar.InitialsAvatar
import com.github.jangalinski.kobweb.tabler.card.TablerCards
import com.github.jangalinski.kobweb.tabler.icon.TablerIcon
import com.github.jangalinski.kobweb.tabler.site.SiteRoutes
import com.github.jangalinski.kobweb.tabler.site.siteLayoutData
import com.github.jangalinski.kobweb.tabler.site.sitePageMeta
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initAvatarsPage(ctx: InitRouteContext) {
  ctx.data.add(sitePageMeta("Avatars", "Avatars display a photo, icon, or initials to represent a person, brand, or status."))
  ctx.data.add(siteLayoutData(SiteRoutes.Avatars))
}

@Page(routeOverride = SiteRoutes.Avatars)
@Composable
fun Avatars() {
  TablerCards {
    card(title = "Default Avatar", width = GridWidth.THIRD) {
      MarkdownText("The base `.avatar` element — a placeholder box for a photo, icon, or initials.")

//      KSpan(modifier = cssAvatar) {

//
//      Svg(attrs = Modifier.classNames("icon").toAttrs {  }) {
//        Text("Gray") {
//          x(10)
//          y(30)
//          fontSize(20)
//        }
//        Text("800") {
//          x(12)
//          y(60)
//          fontSize(20)
//        }
//      }
//      }
      AvatarList(
        avatars = arrayOf(IconAvatar(icon = TablerIcon.TI_USER), InitialsAvatar(initials = Initials("AB")))

      )

    }

    card(title = "Avatar with icon", width = GridWidth.THIRD) {
      MarkdownText("Put an icon inside the `avatar` instead of a photo.")

      AvatarList(
        IconAvatar(icon = TablerIcon.TI_USER),
        IconAvatar(icon = TablerIcon.TI_SETTINGS),
        IconAvatar(icon = TablerIcon.TI_CAR),
        IconAvatar(icon = TablerIcon.TI_BALLOON),
        IconAvatar(icon = TablerIcon.TI_USERS),
        IconAvatar(icon = TablerIcon.TI_USERS_GROUP),
        IconAvatar(icon = TablerIcon.TI_APPS),
        IconAvatar(icon = TablerIcon.TI_GHOST),
      )

    }
  }
  TablerCards {
    card(title = "Cards", width = HALF) {
      P { Text("TablerCard and TablerCards provide the basic card layout.") }

      MarkdownText(
        """
        Avatars display a photo, icon, or initials to represent a person, brand, or status.

        ## Usage
        ```kotlin

        TablerAvatar(
            src = "https://avatars.githubusercontent.com/u/12345678?v=4",
            alt = "User Avatar",
            size = TablerAvatarSize.Medium,
            )

        ```
      """.trimIndent()
      )

    }
    card(title = "Statistics ..... 1", width = HALF) {
      P { Text("Stat cards are useful for compact values and summaries.") }

      TablerIcon.entries.forEach { icon -> icon() }
    }
  }
}
