package com.github.jangalinski.kobweb.tabler.avatar

import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.github.jangalinski.kobweb.tabler._foundation.Initials as AvatarInitials
import com.github.jangalinski.kobweb.tabler._foundation.Image as AvatarImage
import com.github.jangalinski.kobweb.tabler.icon.Icon as AvatarIcon

/** The supported typed content of an [Avatar]. */
sealed interface AvatarContent {
  /** An icon rendered inside an avatar. */
  data class Icon(val content: AvatarIcon) : AvatarContent, Tabler.Component by content

  /** Initials rendered inside an avatar. */
  data class Initials(val content: AvatarInitials) : AvatarContent, Tabler.Component by content

  /** A resource image rendered as an avatar background. */
  data class Image(val content: AvatarImage.Resource) : AvatarContent, Tabler.Component by content
}
