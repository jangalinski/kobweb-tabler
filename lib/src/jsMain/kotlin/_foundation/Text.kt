package com.github.jangalinski.kobweb.tabler._foundation

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KSpan
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.varabyte.kobweb.compose.ui.Modifier

interface Text : TabwebTextComponent {

  @Composable
  override fun invoke(modifier: Modifier) {
    if (value.trim().isEmpty())
      return

    if (modifier == Modifier) {
      KText(value)
    } else {
      KSpan(modifier) {
        KText(value)
      }
    }
  }
}
