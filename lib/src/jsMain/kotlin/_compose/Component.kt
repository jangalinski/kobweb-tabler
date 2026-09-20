package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier

fun interface Component {

  @Composable
  fun compose() = compose(Modifier)

  @Composable
  fun compose(modifier: Modifier)
}
