package com.github.jangalinski.kobweb.tabler

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._app.TablerLayout
import com.github.jangalinski.kobweb.tabler._app.TablerSiteConfig
import com.github.jangalinski.kobweb.tabler._app.ProvideTablerAppState
import com.github.jangalinski.kobweb.tabler._app.ProvideTablerSiteConfig
import com.github.jangalinski.kobweb.tabler._app.TablerSettings
import com.github.jangalinski.kobweb.tabler._app.rememberTablerAppState
import com.github.jangalinski.kobweb.tabler._foundation.Markdown
import com.github.jangalinski.kobweb.tabler._foundation.Url.Internal
import com.github.jangalinski.kobweb.tabler.avatar.AvatarComposable
import com.github.jangalinski.kobweb.tabler.avatar.AvatarDsl
import com.github.jangalinski.kobweb.tabler.badge.BadgeComposable
import com.github.jangalinski.kobweb.tabler.badge.BadgeDsl
import com.github.jangalinski.kobweb.tabler.button.ButtonComposable
import com.github.jangalinski.kobweb.tabler.button.ButtonDsl
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.core.KobwebApp
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.navigation.BasePath

data object KobwebTabler :
  AvatarComposable by AvatarDsl,
  BadgeComposable by BadgeDsl,
  ButtonComposable by ButtonDsl {
  const val TABLER_LAYER = "kobweb-tabler"
  const val TABLER_LAYOUT = "com.github.jangalinski.kobweb.tabler.KobwebTabler.Layout"
  val HOME = Internal("/")

  @Composable
  fun markdown(markdown: String, modifier: Modifier = Modifier) {
    Markdown(markdown).invoke(modifier)
  }

  @Layout
  @Composable
  fun Layout(ctx: PageContext, content: @Composable () -> Unit) {
    TablerLayout(ctx, content)
  }

  /**
   * Installs Kobweb, site defaults, and reactive Tabler settings for an application.
   *
   * Applications that already own their [KobwebApp] wrapper can instead use
   * [ProvideTablerAppState] and [ProvideTablerSiteConfig].
   */
  @Composable
  fun KobwebTablerApp(
    site: TablerSiteConfig = TablerSiteConfig(),
    settings: TablerSettings = TablerSettings(),
    content: @Composable () -> Unit,
  ) {
    val state = rememberTablerAppState(settings)

    KobwebApp {
      ProvideTablerAppState(state) {
        ProvideTablerSiteConfig(site, content)
      }
    }
  }

  fun publicResourcePath(fileName: String) : String = if (fileName.startsWith("/"))
    BasePath.prependTo(fileName)
  else
    publicResourcePath("/$fileName")
}
