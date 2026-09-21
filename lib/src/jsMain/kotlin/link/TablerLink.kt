package com.github.jangalinski.kobweb.tabler.link

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Text

@Composable
fun TablerLink(href:String) {

  A(attrs = {
    attr("href", href)
  }) {
    Text(href)
  }

}
