package com.github.jangalinski.kobweb.tabler.avatar

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import com.github.jangalinski.kobweb.tabler.KobwebTabler.avatar
import com.github.jangalinski.kobweb.tabler.KobwebTabler.avatars
import com.github.jangalinski.kobweb.tabler._foundation.Image
import com.github.jangalinski.kobweb.tabler._foundation.Initials
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test

@OptIn(ComposeWebExperimentalTestsApi::class)
class AvatarListTest {

  @Test
  fun rendersDslAvatarsWithListAndAvatarProperties() = runTest {
    composition {
      AvatarList(avatars = Avatar(Initials("CD")))()
      avatar(content = Initials("EF"))
      avatars(stacked = true, size = AvatarListSize.L) {
        avatar(size = AvatarSize.S, style = AvatarStyle.SQUARE) {
          initials(Initials("AB"))
        }
        avatar(Image("https://example.test/avatar.jpg"))
      }
    }

    val html = root.innerHTML

    assertThat(html).contains("avatar-list")
    assertThat(html).contains("avatar-list-stacked")
    assertThat(html).contains("avatar-list-lg")
    assertThat(html).contains("avatar-sm")
    assertThat(html).contains("avatar-square")
    assertThat(html).contains("CD")
    assertThat(html).contains("EF")
    assertThat(html).contains("AB")
    assertThat(html).contains("https://example.test/avatar.jpg")
    assertThat(root.querySelectorAll(".avatar-list").length).isEqualTo(2)
    assertThat(root.querySelectorAll(".avatar").length).isEqualTo(4)
  }
}
