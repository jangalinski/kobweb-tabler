package net.janhoo.kotlin.kobweb.tabler.models

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import net.janhoo.kotlin.kobweb.tabler.components.TablerNavigation

/**
 * Data supplied before route render for the shared Tabler layout shell.
 */
data class TablerLayoutData(
  val navigation: TablerNavigation = TablerNavigation.None,
  /** Footer content rendered by the shared Tabler shell. */
  val footer: @Composable ColumnScope.() -> Unit = {},
)

/**
 * Page metadata consumed by the shared Tabler layout.
 */
data class TablerPageMeta(
  val title: String,
  val subtitle: String? = null,
  /** Breadcrumbs rendered above the page title in the shared header. */
  val breadcrumbs: List<BreadcrumbItem> = emptyList(),
)
