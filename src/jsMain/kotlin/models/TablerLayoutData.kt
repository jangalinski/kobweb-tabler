package net.janhoo.kotlin.kobweb.tabler.models

import net.janhoo.kotlin.kobweb.tabler.components.TablerNavigation

/**
 * Data supplied before route render for the shared Tabler layout shell.
 */
data class TablerLayoutData(
  val navigation: TablerNavigation = TablerNavigation.None,
)

/**
 * Page metadata consumed by the shared Tabler layout.
 */
data class TablerPageMeta(
  val title: String,
  val subtitle: String? = null,
)
