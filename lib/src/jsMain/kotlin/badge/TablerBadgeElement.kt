package com.github.jangalinski.kobweb.tabler.badge

import androidx.compose.runtime.Composable

const val BADGE = "badge"

@Composable
fun TablerBadge() {
  TODO()
}

data class TablerBadgeData(
  val text: String,
  val size: TablerBadgeSize = TablerBadgeSize.MEDIUM,
  val color: String = "primary",
  val pill: Boolean = false,
  val outline: Boolean = false,
)

