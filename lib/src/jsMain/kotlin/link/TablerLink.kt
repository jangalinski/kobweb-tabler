package com.github.jangalinski.kobweb.tabler.link

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KAnchor
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText

@Composable
fun TablerLink(href:String) {

  KAnchor(href = href) {
    KText(href)
  }

}
