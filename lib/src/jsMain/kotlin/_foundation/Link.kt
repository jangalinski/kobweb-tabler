package com.github.jangalinski.kobweb.tabler._foundation

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KAnchor
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.varabyte.kobweb.compose.ui.Modifier

interface Link : Tabler.Component {
  val href: Url
  val text: String

  @Composable
  override fun invoke(modifier: Modifier) {
    KAnchor(href = href, modifier = modifier) {
      KText(text)
    }
  }
}
