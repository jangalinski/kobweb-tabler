package com.github.jangalinski.kobweb.tabler

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.github.jangalinski.kobweb.tabler._app.LocalTablerAppState
import com.github.jangalinski.kobweb.tabler._app.ProvideTablerAppState
import com.github.jangalinski.kobweb.tabler._app.TablerAppState
import com.github.jangalinski.kobweb.tabler._app.TablerSettings
import com.github.jangalinski.kobweb.tabler._app.TablerTheme
import kotlinx.browser.document
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test

@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerAppStateTest {

  @Test
  fun providesStateToDescendants() = runTest {
    val state = TablerAppState(TablerSettings(theme = TablerTheme.Dark))
    var observed: TablerAppState? = null

    composition {
      ProvideTablerAppState(state) {
        observed = LocalTablerAppState.current
      }
    }

    assertThat(observed).isEqualTo(state)
  }

  @Test
  fun synchronizesExplicitThemeToDocument() = runTest {
    val state = TablerAppState(TablerSettings(theme = TablerTheme.Light))

    composition {
      ProvideTablerAppState(state) {}
    }

    assertThat(document.documentElement?.getAttribute("data-bs-theme")).isEqualTo("light")
  }

  @Test
  fun removesThemeAttributeForSystemMode() = runTest {
    val state = TablerAppState(TablerSettings(theme = TablerTheme.System))
    document.documentElement?.setAttribute("data-bs-theme", "dark")

    composition {
      ProvideTablerAppState(state) {}
    }

    assertThat(document.documentElement?.getAttribute("data-bs-theme")).isNull()
  }

  @Test
  fun synchronizesDocumentWhenThemeChanges() = runTest {
    val state = TablerAppState(TablerSettings(theme = TablerTheme.Light))

    composition {
      ProvideTablerAppState(state) {}
    }

    state.setTheme(TablerTheme.Dark)

    assertThat(state.settings).isEqualTo(TablerSettings(theme = TablerTheme.Dark))
    waitForRecompositionComplete()
    assertThat(document.documentElement?.getAttribute("data-bs-theme")).isEqualTo("dark")
  }
}
