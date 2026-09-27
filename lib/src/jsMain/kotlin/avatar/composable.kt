package com.github.jangalinski.kobweb.tabler.avatar

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.model.Initials
import com.github.jangalinski.kobweb.tabler.icon.Icon
import com.varabyte.kobweb.compose.ui.Modifier

@Composable
fun Avatar(icon : Icon, modifier: Modifier = Modifier) {
  IconAvatar(icon).invoke(modifier)
}

@Composable
fun Avatar(initials: Initials, modifier: Modifier = Modifier) {
  InitialsAvatar(initials).invoke(modifier)
}

@Composable
fun AvatarList(
  vararg avatars: Avatar,
  modifier: Modifier = Modifier
) {
  KDiv(modifier = modifier then cssAvatarList) {
    avatars.forEach { it.invoke() }
  }
}
