package com.github.jangalinski.kobweb.tabler._app

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Stable, observable owner of the current [TablerSettings].
 *
 * Settings changes replace the immutable snapshot, allowing Compose and the
 * document synchronization layer to react consistently.
 */
@Stable
class TablerAppState(initialSettings: TablerSettings) {

  var settings: TablerSettings by mutableStateOf(initialSettings)
    private set

  /** Replaces [settings] with the result of [transform]. */
  fun update(transform: (TablerSettings) -> TablerSettings) {
    settings = transform(settings)
  }

  /** Selects [theme] as the current Tabler color mode. */
  fun setTheme(theme: TablerTheme) {
    update { it.copy(theme = theme) }
  }
}
