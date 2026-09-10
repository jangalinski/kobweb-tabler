package com.github.jangalinski.kobweb.tabler.site

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

/** Builds the site-specific content inside the shared Tabler footer. */
fun siteFooter(): @Composable ColumnScope.() -> Unit = {
  Div(attrs = { attr("class", ClassNames.footerRow) }) {
    Div(attrs = { attr("class", ClassNames.footerLeft) }) {
      Text("kobweb-tabler component documentation")
    }
    Div(attrs = { attr("class", ClassNames.footerRight) }) {
      A(
        href = "https://github.com/jangalinski/kobweb-tabler",
        attrs = {
          attr("class", ClassNames.footerLink)
          attr("target", "_blank")
          attr("rel", "noopener noreferrer")
        },
      ) {
        Text("GitHub")
      }
    }
  }
}
