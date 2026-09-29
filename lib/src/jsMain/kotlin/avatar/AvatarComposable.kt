package com.github.jangalinski.kobweb.tabler.avatar

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.Image
import com.github.jangalinski.kobweb.tabler._foundation.Initials
import com.github.jangalinski.kobweb.tabler._foundation.modifier.BackgroundColor
import com.github.jangalinski.kobweb.tabler.icon.Icon
import com.varabyte.kobweb.compose.ui.Modifier

/** Page-level composable entry points for the avatar concept. */
interface AvatarComposable {

  /** Renders an icon [Avatar] without creating an instance at the call site. */
  @Composable
  fun avatar(
    content: Icon,
    size: AvatarSize = AvatarSize.DEFAULT,
    color: BackgroundColor = BackgroundColor.DEFAULT,
    style: AvatarStyle = AvatarStyle.DEFAULT,
    modifier: Modifier = Modifier,
  )

  /** Renders an initials [Avatar] without creating an instance at the call site. */
  @Composable
  fun avatar(
    content: Initials,
    size: AvatarSize = AvatarSize.DEFAULT,
    color: BackgroundColor = BackgroundColor.DEFAULT,
    style: AvatarStyle = AvatarStyle.DEFAULT,
    modifier: Modifier = Modifier,
  )

  /** Renders an image [Avatar] without creating an instance at the call site. */
  @Composable
  fun avatar(
    content: Image.Resource,
    size: AvatarSize = AvatarSize.DEFAULT,
    style: AvatarStyle = AvatarStyle.DEFAULT,
    modifier: Modifier = Modifier,
  )

  /** Renders an [AvatarList] built by the typed [AvatarListScope] DSL. */
  @Composable
  fun avatars(
    stacked: Boolean = false,
    size: AvatarListSize = AvatarListSize.DEFAULT,
    modifier: Modifier = Modifier,
    content: AvatarListScope.() -> Unit,
  )
}
