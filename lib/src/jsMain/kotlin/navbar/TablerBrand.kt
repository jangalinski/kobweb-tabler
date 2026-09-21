package com.github.jangalinski.kobweb.tabler.navbar

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Component
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.css.plus
import com.github.jangalinski.kobweb.tabler._foundation.HOME
import com.github.jangalinski.kobweb.tabler._foundation.Url
import com.github.jangalinski.kobweb.tabler.navbar.TablerNavbarCss.NAVBAR_BRAND
import com.github.jangalinski.kobweb.tabler.navbar.TablerNavbarCss.NAVBAR_BRAND_AUTODARK
import com.github.jangalinski.kobweb.tabler._foundation.css.PE_0
import com.github.jangalinski.kobweb.tabler._foundation.css.PE_MD_3
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.navigation.BasePath
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text


/** Renders the brand area used by a Tabler navbar. */
data object TablerBrand {
  sealed interface Brand : Component {
    val image: Url
    val href: Url get() = HOME

    /** A compact logo accompanied by a textual caption. */
    data class Logo(override val image: Url, val caption: String = "Tabler") : Brand {
      @Composable
      override fun invoke(modifier: Modifier) {
        A(href = BasePath.prependTo(href.value), attrs = {
          attr("class", "text-reset text-decoration-none")
          attr("aria-label", caption)
        }) {
          Img(src = image.value, attrs = {
            attr("class", "navbar-brand-image")
            attr("alt", "")
          })
          Text(caption)
        }
      }
    }

    /** A wordmark image without additional text. */
    data class Wordmark(override val image: Url) : Brand {
      @Composable
      override fun invoke(modifier: Modifier) {
        A(href = BasePath.prependTo(href.value), attrs = {
          attr("class", "text-reset text-decoration-none")
          attr("aria-label", "Home")
        }) {
          Img(src = image.value, attrs = {
            attr("class", "navbar-brand-image")
            attr("alt", "")
          })
        }
      }
    }
  }

  @Composable
  operator fun invoke(brand: Brand) {
    KDiv(NAVBAR_BRAND + NAVBAR_BRAND_AUTODARK + PE_0 + PE_MD_3) {
      brand()
    }
  }
}
//internal fun TablerNavigation.HeaderNavigation.renderNavbar(includeBrand: Boolean = true) {
//  Div(attrs = modifier.toAttrs()) {
//    Header(attrs = { attr("class", ClassNames.navbar) }) {
//      Div(attrs = { attr("class", ClassNames.containerXl) }) {
//        Button(attrs = {
//          attr("class", ClassNames.navbarToggler)
//          attr("type", "button")
//          attr("data-bs-toggle", "collapse")
//          attr("data-bs-target", "#$NAVBAR_MENU_ID")
//          attr("aria-controls", NAVBAR_MENU_ID)
//          attr("aria-expanded", "false")
//          attr("aria-label", "Toggle primary navigation")
//        }) {
//          Span(attrs = { attr("class", ClassNames.navbarTogglerIcon) })
//        }
//        if (includeBrand) {
//          renderBrand(
//            brandClass = ClassNames.navbarBrand,
//            title = title,
//            caption = caption,
//            logo = logo,
//            href = href,
//          )
//        }
//        Div(attrs = { attr("class", "${ClassNames.navbarNav} ${ClassNames.msAuto}") }) {
//          content()
//        }
//      }
//    }
//    Div(attrs = { attr("class", ClassNames.navbarExpandMd) }) {
//      Div(attrs = {
//        attr("class", ClassNames.navbarCollapse)
//        attr("id", NAVBAR_MENU_ID)
//      }) {
//        Div(attrs = { attr("class", ClassNames.navbar) }) {
//          Div(attrs = { attr("class", ClassNames.containerXl) }) {
//            Nav(attrs = { attr("aria-label", "Primary") }) {
//              Ul(attrs = { attr("class", ClassNames.navbarNavPrimary) }) {
//                renderNavItems(items)
//              }
//            }
//          }
//        }
//      }
//    }
//  }
//}
