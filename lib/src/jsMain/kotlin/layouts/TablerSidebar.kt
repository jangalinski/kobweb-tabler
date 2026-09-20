package com.github.jangalinski.kobweb.tabler._compose

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerNavigation
import com.github.jangalinski.kobweb.tabler.components.renderSidebar

/**
 * Sidebar navigation block for a Tabler page shell.
 *
 * This corresponds to the preview's `<!-- BEGIN SIDEBAR -->` region and is
 * the vertical-navigation counterpart to [TablerNavbar]. It belongs to the
 * shared Kobweb layout, not to an individual page.
 */
@Composable
fun TablerSidebar(navigation: TablerNavigation.SidebarNavigation) {
  navigation.renderSidebar()
}
