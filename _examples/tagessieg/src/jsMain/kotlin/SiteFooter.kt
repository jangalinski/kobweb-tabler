package net.janhoo.kotlin.kobweb.tabler.example.tagessieg

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

/**
 * Builds the site footer block consumed by the shared Tabler layout.
 *
 * The footer keeps the left side as a lightweight project note and the right side as
 * a pair of outbound repository links. The actual URLs stay in the site module so the
 * shared Tabler library remains reusable.
 */
fun siteFooter(): @Composable ColumnScope.() -> Unit = {
  Div(attrs = { attr("class", ClassNames.footerRow) }) {
    Div(attrs = { attr("class", ClassNames.footerLeft) }) {
      Text("Generated with kobweb tabler")
    }

    Div(attrs = { attr("class", ClassNames.footerRight) }) {
      repoLink(
        label = "bstdoom/tagessieg",
        href = "https://github.com/bstdoom/tagessieg",
      )
      repoLink(
        label = "janhoo-net/kobweb-tabler",
        href = "https://github.com/janhoo-net/kobweb-tabler",
      )
    }
  }
}

/**
 * Renders a single footer link with the shared GitHub brand icon.
 */
@Composable
private fun repoLink(
  label: String,
  href: String,
) {
  A(
    href = href,
    attrs = {
      attr("class", ClassNames.footerLink)
      attr("target", "_blank")
      attr("rel", "noopener noreferrer")
    },
  ) {
    Img(
      src = svgDataUri(SiteIcons.Github.svg),
      alt = SiteIcons.Github.altText ?: "GitHub",
      attrs = {
        attr("class", ClassNames.footerLinkIcon)
        attr("width", "16")
        attr("height", "16")
      },
    )
    Text(label)
  }
}

/**
 * Converts inline SVG markup into a data URI so it can be used as an image source.
 */
private fun svgDataUri(svg: String): String =
  "data:image/svg+xml;charset=UTF-8,${encodeURIComponent(svg)}"

@Suppress("UnsafeCastFromDynamic")
private fun encodeURIComponent(value: String): String = js("encodeURIComponent(value)") as String
