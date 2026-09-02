package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.varabyte.kobweb.compose.css.TextTransform
import com.varabyte.kobweb.compose.css.textTransform
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.AppGlobals
import com.github.jangalinski.kobweb.tabler.models.Image
import com.github.jangalinski.kobweb.tabler.models.NavigationItem
import com.github.jangalinski.kobweb.tabler.models.navigationItems
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import com.github.jangalinski.kobweb.tabler.styles.ClassNames.modifier
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Aside
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import kotlinx.browser.document
import org.w3c.dom.Element
import org.w3c.dom.events.Event

/**
 * Sealed navigation slot for a Tabler page shell.
 *
 * Exactly one navigation mode should be used at a time.
 */
sealed interface TablerNavigation {

  /**
   * Modifier applied to the navigation component container.
   */
  val modifier: Modifier

  /**
   * No navigation block.
   */
  data object None : TablerNavigation {
    override val modifier: Modifier = Modifier
  }

  /**
   * Header-style navigation rendered above the page content.
   */
  data class HeaderNavigation(
    override val modifier: Modifier = Modifier,
    val title: String = AppGlobals["title"] ?: throw IllegalArgumentException("Title is required for header navigation"),
    val caption: String? = null,
    val logo: Image = Image.None,
    val href: String? = null,
    val items: List<NavigationItem> = emptyList(),
    val content: @Composable () -> Unit = {},
  ) : TablerNavigation

  /**
   * Sidebar-style navigation rendered beside the page content.
   */
  data class SidebarNavigation(
    override val modifier: Modifier = Modifier,
    val title: String,
    val caption: String? = null,
    val logo: Image = Image.None,
    val href: String? = null,
    val items: List<NavigationItem> = emptyList(),
    val content: @Composable () -> Unit = {},
  ) : TablerNavigation
}

private const val SIDEBAR_MENU_ID = "sidebar-menu"

/**
 * Renders the selected navigation block.
 */
@Composable
internal fun TablerNavigation.render() {
  when (this) {
    TablerNavigation.None -> Unit
    is TablerNavigation.HeaderNavigation -> renderHeaderNavigation()
    is TablerNavigation.SidebarNavigation -> renderSidebarNavigation()
  }
}

@Composable
private fun TablerNavigation.HeaderNavigation.renderHeaderNavigation() {
  Box(modifier = modifier) {
    Header(attrs = {
      attr("class", ClassNames.navbarHeader)
    }) {
      Div(attrs = {
        attr(
          "class",
          "${ClassNames.containerXl} ${ClassNames.dFlex} ${ClassNames.alignItemsCenter}",
        )
      }) {
        renderBrand(
          brandClass = ClassNames.navbarBrandHeader,
          title = title,
          caption = caption,
          logo = logo,
          href = href,
        )
        Div(attrs = {
          attr("class", "${ClassNames.navbarNavHeader} ${ClassNames.ms3}")
        }) {
          renderNavItems(items)
        }
        Div(attrs = {
          attr("class", ClassNames.flexGrow1)
        })
        Div(attrs = {
          attr("class", "${ClassNames.dFlex} ${ClassNames.alignItemsCenter} ${ClassNames.msAuto}")
        }) {
          content()
        }
      }
    }
  }
}

@Composable
private fun TablerNavigation.SidebarNavigation.renderSidebarNavigation() {
  Box(modifier = modifier) {
    Aside(attrs = {
      attr("class", ClassNames.navbarVertical)
      attr("data-bs-theme", "dark")
    }) {
      Box(modifier = ClassNames.containerFluid.modifier()) {
        Button(
          attrs = {
            attr("class", ClassNames.navbarToggler)
            attr("type", "button")
            attr("data-bs-toggle", "collapse")
            attr("data-bs-target", "#$SIDEBAR_MENU_ID")
            attr("aria-controls", SIDEBAR_MENU_ID)
            attr("aria-expanded", "false")
            attr("aria-label", "Toggle navigation")
          },
        ) {
          Span(attrs = { attr("class", ClassNames.navbarTogglerIcon) })
        }

        renderBrand(
          brandClass = ClassNames.navbarBrandSidebar,
          title = title,
          caption = caption,
          logo = logo,
          href = href,
        )

        Div(attrs = {
          attr("class", ClassNames.navbarCollapse)
          attr("id", SIDEBAR_MENU_ID)
        }) {
          Div(attrs = {
            attr("class", ClassNames.navbarNavSidebar)
          }) {
            renderNavItems(items)
            content()
          }
        }
      }
    }
  }
}

@Composable
private fun renderBrand(
  brandClass: String,
  title: String,
  caption: String?,
  logo: Image,
  href: String?,
) {
  A(attrs = {
    attr(
      "class",
      "$brandClass ${ClassNames.navLink} ${ClassNames.textReset} ${ClassNames.textDecorationNone}",
    )
    if (!href.isNullOrBlank()) {
      attr("href", href)
    }
  }) {
    Div(attrs = {
      attr("class", "${ClassNames.dFlex} ${ClassNames.alignItemsCenter}")
    }) {
      renderImage(logo, defaultAlt = title, className = ClassNames.navbarBrandImage)
      Div(attrs = { attr("class", ClassNames.dFlexColumn) }) {
        Span(
          attrs = {
            style {
              textTransform(TextTransform.Lowercase)
            }
          }
        ) {
          Text(title)
        }
        caption?.let {
          Span(attrs = { attr("class", ClassNames.smallTextSecondary) }) {
            Text(it)
          }
        }
      }
    }
  }
}

@Composable
private fun renderNavItems(items: List<NavigationItem>) {
  items.forEach { item ->
    when (item) {
      is NavigationItem.Link -> renderNavLink(item)
      is NavigationItem.Dropdown -> renderNavDropdown(item)
    }
  }
}

/**
 * Builds a hierarchy of navigation items.
 */
fun navItems(block: com.github.jangalinski.kobweb.tabler.models.NavigationItemsBuilder.() -> Unit): List<NavigationItem> =
  navigationItems(block)

@Composable
private fun renderNavLink(item: NavigationItem.Link) {
  val linkClass = if (item.active) {
    "${ClassNames.navLink} ${ClassNames.navLinkActive}"
  } else {
    ClassNames.navLink
  }
  Div(attrs = {
    attr("class", if (item.active) "${ClassNames.navItem} ${ClassNames.navItemActive}" else ClassNames.navItem)
  }) {
    A(attrs = {
      attr("class", linkClass)
      attr("href", item.href)
      if (item.active) {
        attr("aria-current", "page")
      }
    }) {
      renderImage(item.icon, defaultAlt = item.name, className = ClassNames.navItemIcon)
      Span(attrs = { attr("class", ClassNames.navLinkTitle) }) {
        Text(item.name)
      }
    }
  }
}

@Composable
private fun renderNavDropdown(item: NavigationItem.Dropdown) {
  val dropdownId = dropdownId(item.name)
  DisposableEffect(dropdownId) {
    val listener = fun(event: Event) {
      val target = event.target as? Element ?: return
      val root = document.getElementById(dropdownId) ?: return
      if (root.contains(target)) return
      root.querySelector(".dropdown-menu")?.classList?.remove("show")
    }

    document.addEventListener("click", listener)
    onDispose {
      document.removeEventListener("click", listener)
    }
  }

  Div(attrs = {
    attr(
      "class",
      if (item.active) {
        "${ClassNames.navItemDropdown} ${ClassNames.positionRelative} ${ClassNames.navItemActive}"
      } else {
        "${ClassNames.navItemDropdown} ${ClassNames.positionRelative}"
      },
    )
    attr("id", dropdownId)
  }) {
    Div(attrs = {
      attr("class", "${ClassNames.dFlex} ${ClassNames.alignItemsCenter}")
    }) {
      A(attrs = {
        attr(
          "class",
          if (item.active) {
            "${ClassNames.navLink} ${ClassNames.navLinkActive}"
          } else {
            ClassNames.navLink
          },
        )
        attr("href", item.href)
        if (item.active) {
          attr("aria-current", "page")
        }
      }) {
        renderImage(item.icon, defaultAlt = item.name, className = ClassNames.navItemIcon)
        Span(attrs = { attr("class", ClassNames.navLinkTitle) }) {
          Text(item.name)
        }
      }
      Button(attrs = {
        attr("type", "button")
        attr(
          "class",
          if (item.active) {
            "${ClassNames.navLinkDropdownToggleButton} ${ClassNames.navLinkActive}"
          } else {
            ClassNames.navLinkDropdownToggleButton
          },
        )
        attr("aria-expanded", "false")
        attr("aria-label", "Open ${item.name} menu")
        attr(
          "onclick",
          "const root=document.getElementById('$dropdownId'); const menu=root && root.querySelector('.dropdown-menu'); if(menu){menu.classList.toggle('show');}",
        )
      }) {
      }
    }

    Div(attrs = {
      attr(
        "class",
        buildString {
          append(ClassNames.dropdownMenu)
          append(' ')
          append(ClassNames.dropdownMenuArrow)
          append(' ')
          append(ClassNames.dropdownMenuBelow)
        },
      )
    }) {
      item.items.forEach { dropdownItem ->
        A(attrs = {
          attr(
            "class",
            if (dropdownItem.active) {
              "${ClassNames.dropdownItem} ${ClassNames.dropdownItemActive}"
            } else {
              ClassNames.dropdownItem
            },
          )
          attr("href", dropdownItem.href)
          if (dropdownItem.active) {
            attr("aria-current", "page")
          }
        }) {
          renderImage(dropdownItem.icon, defaultAlt = dropdownItem.name, className = ClassNames.dropdownItemIcon)
          Text(dropdownItem.name)
        }
      }
    }
  }
}

private fun dropdownId(name: String): String =
  "nav-dropdown-" + name.lowercase().replace(Regex("[^a-z0-9]+"), "-").trim('-')
