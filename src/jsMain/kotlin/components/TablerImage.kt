package io.github.jangalinski.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import io.github.jangalinski.kotlin.kobweb.tabler.KobwebTabler.publicResourcePath
import io.github.jangalinski.kotlin.kobweb.tabler.models.Image
import io.github.jangalinski.kotlin.kobweb.tabler.styles.ClassNames
import org.jetbrains.compose.web.dom.Img

/**
 * Renders a Tabler image or inline SVG with the icon sizing used by the shared shell.
 *
 * The helper keeps icon rendering consistent across navbar, breadcrumbs, and any other
 * shared layout elements that need small inline icons.
 */
@Composable
internal fun renderImage(image: Image?, defaultAlt: String, className: String) {
  val size = when (className) {
    ClassNames.navbarBrandImage -> 40
    ClassNames.navItemIcon -> 16
    ClassNames.dropdownItemIcon -> 16
    else -> null
  }
  when (image) {
    null -> Unit
    is Image.None -> Unit
    is Image.ImageResource -> Img(
      src = publicResourcePath(image.resource),
      alt = image.altText ?: defaultAlt,
      attrs = {
        attr(
          "class",
          when (className) {
            ClassNames.navbarBrandImage -> "${className} ${ClassNames.me2}"
            ClassNames.navItemIcon -> "${className} ${ClassNames.me2}"
            ClassNames.dropdownItemIcon -> "${className} ${ClassNames.me2}"
            else -> className
          },
        )
        size?.let {
          attr("width", it.toString())
          attr("height", it.toString())
        }
      },
    )
    is Image.InlineSvg -> Img(
      src = svgDataUri(image.svg),
      alt = image.altText ?: defaultAlt,
      attrs = {
        attr(
          "class",
          when (className) {
            ClassNames.navbarBrandImage -> "$className ${ClassNames.me2}"
            ClassNames.navItemIcon -> "$className ${ClassNames.me2}"
            ClassNames.dropdownItemIcon -> "$className ${ClassNames.me2}"
            else -> className
          },
        )
        size?.let {
          attr("width", it.toString())
          attr("height", it.toString())
        }
      },
    )
  }
}

/**
 * Converts inline SVG markup into a data URI so it can be used as an image source.
 */
internal fun svgDataUri(svg: String): String =
  "data:image/svg+xml;charset=UTF-8,${encodeURIComponent(svg)}"

@Suppress("UnsafeCastFromDynamic")
private fun encodeURIComponent(value: String): String = js("encodeURIComponent(value)") as String
