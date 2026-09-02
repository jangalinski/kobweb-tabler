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

/** Content rendered inside a Tabler avatar. */
sealed interface TablerAvatarContent {
  /** An image from the consuming Kobweb application's public resources. */
  data class ImageResource(val resource: String, val altText: String? = null) : TablerAvatarContent

  /** An inline SVG icon. */
  data class Icon(val svg: String, val altText: String? = null) : TablerAvatarContent

  /** Text initials, such as `JG`. */
  data class Initials(val value: String) : TablerAvatarContent
}

/** Tabler light background colors for initials and icons. */
enum class TablerAvatarColor(internal val className: String) {
  BLUE("bg-blue-lt"),
  AZURE("bg-azure-lt"),
  INDIGO("bg-indigo-lt"),
  PURPLE("bg-purple-lt"),
  PINK("bg-pink-lt"),
  RED("bg-red-lt"),
  ORANGE("bg-orange-lt"),
  YELLOW("bg-yellow-lt"),
  LIME("bg-lime-lt"),
  GREEN("bg-green-lt"),
  TEAL("bg-teal-lt"),
  CYAN("bg-cyan-lt"),
  GRAY("bg-secondary-lt"),
  PRIMARY("bg-primary-lt"),
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
