package com.github.jangalinski.kobweb.tabler.avatar

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * Compatibility entry point for the former avatar DSL object.
 */
@Deprecated("Use AvatarDsl or KobwebTabler.avatars.", ReplaceWith("AvatarDsl"))
data object TabwebAvatar {

  /** Renders a list from the legacy `+Avatar(...)` builder syntax. */
  @Composable
  fun avatars(
    stacked: Boolean = false,
    modifier: Modifier = Modifier,
    content: @Composable AvatarListScope.() -> Unit,
  ) {
    val scope = AvatarListScope()
    scope.content()
    AvatarList(stacked = stacked, avatars = scope.avatars)(modifier)
  }

  /** Compatibility scope for the former `TabwebAvatar.avatars` builder. */
  class AvatarListScope internal constructor() {
    internal val avatars = mutableListOf<Avatar>()

    operator fun plus(avatar: Avatar) {
      avatars.add(avatar)
    }
  }
}
