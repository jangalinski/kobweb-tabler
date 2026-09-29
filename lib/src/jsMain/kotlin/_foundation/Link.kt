package com.github.jangalinski.tabweb._foundation

import androidx.compose.runtime.Composable
import com.github.jangalinski.tabweb._foundation.compose.KAnchor
import com.github.jangalinski.tabweb._foundation.compose.KText
import com.varabyte.kobweb.compose.ui.Modifier

interface Link : TabwebFoundationComponent {
  val href: Url
  val text: String

  @Composable
  override fun invoke(modifier: Modifier) {
    KAnchor(href = href, modifier = modifier) {
      KText(text)
    }
  }
}
