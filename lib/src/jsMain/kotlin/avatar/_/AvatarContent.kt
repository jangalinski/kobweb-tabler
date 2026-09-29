package com.github.jangalinski.kobweb.tabler.avatar._

import com.github.jangalinski.kobweb.tabler._foundation.Tabler
import com.varabyte.kobweb.compose.css.BackgroundImage
import com.github.jangalinski.kobweb.tabler._foundation.Image.Resource as _Image
import com.github.jangalinski.kobweb.tabler._foundation.Initials as _Initials
import com.github.jangalinski.kobweb.tabler.icon.Icon as _Icon

sealed interface AvatarContent {
  data class Icon(val content: _Icon) : AvatarContent, Tabler.Component by content
  data class Initials(val content: _Initials) : AvatarContent, Tabler.Component by content
  data class Image(val content: _Image) : AvatarContent, Tabler.Component by content
}
