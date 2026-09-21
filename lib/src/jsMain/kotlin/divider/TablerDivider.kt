package com.github.jangalinski.kobweb.tabler.divider

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import org.jetbrains.compose.web.dom.Text

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
        Text(text)
      }
    } else {
      KDiv(modifier.then(Modifier.classNames("hr"))) {}
    }
}
