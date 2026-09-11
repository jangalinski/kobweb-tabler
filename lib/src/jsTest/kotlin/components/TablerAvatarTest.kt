package com.github.jangalinski.kobweb.tabler.components

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.doesNotContain
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarColor
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarContent
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarData
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarListData
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarShape
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarSize
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarStatus
import com.github.jangalinski.kobweb.tabler.models.TablerAvatarStatusColor
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
@OptIn(ComposeWebExperimentalTestsApi::class)
class TablerAvatarTest {

  @Test
  fun rendersInitials() = runTest {
    composition {
      TablerAvatar(TablerAvatarData(content = TablerAvatarContent.Initials("JG")))
    }

    val html = root.innerHTML
    assertThat(html).contains("avatar")
    assertThat(html).contains("JG")
  }

  @Test
  fun rendersImageResource() = runTest {
    composition {
      TablerAvatar(TablerAvatarData(content = TablerAvatarContent.ImageResource("images/user.jpg", altText = "User photo")))
    }

    val html = root.innerHTML
    assertThat(html).contains("avatar")
    assertThat(html).contains("<img")
    assertThat(html).contains("user.jpg")
    assertThat(html).contains("User photo")
  }

  @Test
  fun rendersImageResourceWithoutAltText() = runTest {
    composition {
      TablerAvatar(TablerAvatarData(content = TablerAvatarContent.ImageResource("images/anon.png")))
    }

    val html = root.innerHTML
    assertThat(html).contains("<img")
    assertThat(html).contains("anon.png")
  }

  @Test
  fun rendersIconContent() = runTest {
    val svg = """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/></svg>"""
    composition {
      TablerAvatar(TablerAvatarData(content = TablerAvatarContent.Icon(svg, altText = "Circle icon")))
    }

    val html = root.innerHTML
    assertThat(html).contains("avatar")
    assertThat(html).contains("<img")
    assertThat(html).contains("data:image/svg+xml")
    assertThat(html).contains("Circle icon")
  }

  @Test
  fun rendersIconWithIconClass() = runTest {
    val svg = """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><rect width="24" height="24"/></svg>"""
    composition {
      TablerAvatar(TablerAvatarData(content = TablerAvatarContent.Icon(svg)))
    }

    val html = root.innerHTML
    assertThat(html).contains("icon")
  }

  @Test
  fun rendersColorClasses() = runTest {
    composition {
      TablerAvatar(
        TablerAvatarData(
          content = TablerAvatarContent.Initials("AB"),
          color = TablerAvatarColor.BLUE,
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).apply {
      contains("bg-blue")
      contains("text-white")
    }
  }

  @Test
  fun rendersSizeClass() = runTest {
    composition {
      TablerAvatar(
        TablerAvatarData(
          content = TablerAvatarContent.Initials("XL"),
          size = TablerAvatarSize.EXTRA_LARGE,
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("avatar-xl")
  }

  @Test
  fun rendersShapeClass() = runTest {
    composition {
      TablerAvatar(
        TablerAvatarData(
          content = TablerAvatarContent.Initials("CI"),
          shape = TablerAvatarShape.CIRCLE,
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("rounded-circle")
  }

  @Test
  fun rendersAriaLabel() = runTest {
    composition {
      TablerAvatar(
        TablerAvatarData(
          content = TablerAvatarContent.Initials("AL"),
          ariaLabel = "Profile picture",
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("aria-label")
    assertThat(html).contains("Profile picture")
  }

  @Test
  fun rendersStatusBadge() = runTest {
    composition {
      TablerAvatar(
        TablerAvatarData(
          content = TablerAvatarContent.Initials("SB"),
          status = TablerAvatarStatus(color = TablerAvatarStatusColor.SUCCESS),
        ),
      )
    }

    val html = root.innerHTML
    assertThat(html).contains("bg-success")
  }

  @Test
  fun rendersAvatarListWithStackedClass() = runTest {
    val avatars = listOf(
      TablerAvatarData(content = TablerAvatarContent.Initials("A1")),
      TablerAvatarData(content = TablerAvatarContent.Initials("A2")),
    )

    composition {
      TablerAvatarList(TablerAvatarListData(avatars = avatars, stacked = true))
    }

    val html = root.innerHTML
    assertThat(html).contains("avatar-list")
    assertThat(html).contains("avatar-list-stacked")
    assertThat(html).contains("A1")
    assertThat(html).contains("A2")
  }

  @Test
  fun rendersAvatarListWithoutStackedClassByDefault() = runTest {
    val avatars = listOf(TablerAvatarData(content = TablerAvatarContent.Initials("X")))

    composition {
      TablerAvatarList(TablerAvatarListData(avatars = avatars))
    }

    val html = root.innerHTML
    assertThat(html).contains("avatar-list")
    assertThat(html).doesNotContain("avatar-list-stacked")
  }
}
