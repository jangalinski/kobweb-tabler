package com.github.jangalinski.kobweb.tabler._foundation

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier

fun interface Component {

  @Composable
  operator fun invoke(modifier: Modifier)

  @Composable
  operator fun invoke() = invoke(Modifier)
}
