package com.github.jangalinski.kobweb.tabler.card

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.github.jangalinski.kobweb.tabler._foundation.compose.KH1
import com.github.jangalinski.kobweb.tabler._foundation.compose.KP
import com.github.jangalinski.kobweb.tabler._foundation.compose.KSpan
import com.github.jangalinski.kobweb.tabler._foundation.compose.KText
import com.github.jangalinski.kobweb.tabler._foundation.css.ClassNames
import com.github.jangalinski.kobweb.tabler._foundation.css.ClassNames.modifier
import com.github.jangalinski.kobweb.tabler._foundation.css.GridWidth

/**
 * Renders a compact summary card for tabular or numeric statistics.
 */
@Composable
fun TablerStatCard(
  title: String,
  value: String,
  note: String? = null,
  badgeText: String? = null,
  width: GridWidth = GridWidth.QUARTER,
  modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.then(width.classNames.modifier())) {
    TablerCard(
      title = title,
      modifier = ClassNames.h100.modifier(),
    ) {
      KH1(modifier = ClassNames.h1Mb2.modifier()) {
        KText(value)
      }

      note?.let {
        KP(modifier = ClassNames.textSecondaryM0.modifier()) {
          KText(it)
        }
      }

      badgeText?.let {
        Box(modifier = ClassNames.mt3.modifier()) {
          KSpan(modifier = ClassNames.badge.modifier(), text = it)
        }
      }
    }
  }
}
