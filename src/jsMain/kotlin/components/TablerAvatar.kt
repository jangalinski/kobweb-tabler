package com.github.jangalinski.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.KobwebTabler.publicResourcePath
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarContent
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarData
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarListData
import com.github.jangalinski.kobweb.tabler.styles.ClassNames
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/** Renders a single Tabler avatar from pure [TablerAvatarData] configuration. */
@Composable
fun TablerAvatar(data: TablerAvatarData) {
  val content = data.content
  Span(
    attrs = {
      classes(ClassNames.avatar)
      data.color?.let { classes(it.backgroundClassName, it.foregroundClassName) }
      data.size.className?.let(::classes)
      data.shape.className?.let(::classes)
      data.ariaLabel?.let { attr("aria-label", it) }
      if (content is TablerAvatarContent.ImageResource) {
        attr("style", "background-color: var(--tblr-bg-surface)")
      }
    },
  ) {
    when (content) {
      is TablerAvatarContent.ImageResource -> Img(
        src = publicResourcePath(content.resource),
        alt = content.altText.orEmpty(),
        attrs = {
          attr(
            "style",
            "display: block; width: 100%; height: 100%; object-fit: cover; border-radius: inherit",
          )
        },
      )
      is TablerAvatarContent.Icon -> Img(
        src = svgDataUri(content.svg),
        alt = content.altText.orEmpty(),
        attrs = { classes(ClassNames.icon) },
      )
      is TablerAvatarContent.Initials -> Text(content.value)
    }
    data.status?.let { status ->
      Span(attrs = {
        classes(ClassNames.badge, status.color.className)
        if (status.label != null) {
          attr(
            "style",
            "display: flex; align-items: center; justify-content: center; width: 14px; height: 14px; min-width: 0; padding: 0; font-size: 8px; line-height: 1; border-radius: 50%",
          )
        }
      }) {
        status.label?.let { Text(it) }
      }
    }
  }
}

/** Renders Tabler's `avatar-list`, optionally using its overlapping stacked variant. */
@Composable
fun TablerAvatarList(data: TablerAvatarListData) {
  Div(attrs = {
    classes(ClassNames.avatarList)
    if (data.stacked) classes(ClassNames.avatarListStacked)
  }) {
    data.avatars.forEach { avatar ->
      TablerAvatar(avatar)
    }
  }
}
