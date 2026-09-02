package com.github.jangalinski.kobweb.tabler.models

/**
 * Pure configuration for a [com.github.jangalinski.kobweb.tabler.components.TablerAvatar].
 */
data class TablerAvatarData(
  val content: TablerAvatarContent,
  val color: TablerAvatarColor? = null,
  val size: TablerAvatarSize = TablerAvatarSize.DEFAULT,
  val shape: TablerAvatarShape = TablerAvatarShape.DEFAULT,
  val status: TablerAvatarStatus? = null,
  val ariaLabel: String? = null,
)

/** Pure configuration for a horizontal collection of Tabler avatars. */
data class TablerAvatarListData(
  val avatars: List<TablerAvatarData>,
  val stacked: Boolean = false,
)

/** Content rendered inside a Tabler avatar. */
sealed interface TablerAvatarContent {
  /** An image from the consuming Kobweb application's public resources. */
  data class ImageResource(val resource: String, val altText: String? = null) : TablerAvatarContent

  /** An inline SVG icon. */
  data class Icon(val svg: String, val altText: String? = null) : TablerAvatarContent

  /** Text initials, such as `JG`. */
  data class Initials(val value: String) : TablerAvatarContent
}

/** Opaque Tabler background colors for initials and icons. */
enum class TablerAvatarColor(
  internal val backgroundClassName: String,
  internal val foregroundClassName: String = "text-white",
) {
  BLUE("bg-blue"),
  AZURE("bg-azure"),
  INDIGO("bg-indigo"),
  PURPLE("bg-purple"),
  PINK("bg-pink"),
  RED("bg-red"),
  ORANGE("bg-orange"),
  YELLOW("bg-yellow", "text-dark"),
  LIME("bg-lime", "text-dark"),
  GREEN("bg-green"),
  TEAL("bg-teal"),
  CYAN("bg-cyan"),
  GRAY("bg-secondary"),
  PRIMARY("bg-primary"),
}

/** Tabler's documented avatar sizes. */
enum class TablerAvatarSize(internal val className: String?) {
  EXTRA_SMALL("avatar-xs"),
  SMALL("avatar-sm"),
  DEFAULT(null),
  LARGE("avatar-lg"),
  EXTRA_LARGE("avatar-xl"),
}

/** Tabler and Bootstrap avatar shapes. */
enum class TablerAvatarShape(internal val className: String?) {
  DEFAULT(null),
  ROUNDED("rounded"),
  CIRCLE("rounded-circle"),
  SQUARE("rounded-0"),
  LARGE_ROUNDED("rounded-3"),
}

/** Optional badge overlaid on an avatar. */
data class TablerAvatarStatus(
  val color: TablerAvatarStatusColor,
  val label: String? = null,
)

/** Tabler background colors used by an avatar status badge. */
enum class TablerAvatarStatusColor(internal val className: String) {
  SUCCESS("bg-success"),
  WARNING("bg-warning"),
  DANGER("bg-danger"),
  INFO("bg-info"),
  GRAY("bg-gray"),
}
