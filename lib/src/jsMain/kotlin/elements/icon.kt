package com.github.jangalinski.kobweb.tabler.elements

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.IconName
import com.github.jangalinski.kobweb.tabler.KobwebTablerCommon.icon
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.I

@Composable
fun tbIcon(
  name: String,
  modifier: Modifier = Modifier,
) = tbIcon(name = icon(name), modifier = modifier)


@Composable
fun tbIcon(
  name: IconName,
  modifier: Modifier = Modifier,
) {
  I(
    attrs = modifier
      .classNames("ti", "ti-${name.value()}")
      .toAttrs()
  )
}
