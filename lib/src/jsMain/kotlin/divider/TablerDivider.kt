package com.github.jangalinski.kobweb.tabler.divider

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

/**
 * A divider component that can optionally display text.
 *
 * https://docs.tabler.io/ui/components/divider
 */
@Composable
fun TablerDivider(
  modifier: Modifier = Modifier,
  text: String? = null
) {
    if (text != null) {
      KDiv(modifier = modifier.then(Modifier.classNames("hr-text"))) {
        KText(text)
      }
    } else {
      KDiv(modifier.then(Modifier.classNames("hr"))) {}
    }
}
