package com.github.jangalinski.kobweb.tabler.layouts

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler.components.TablerNavigation
import com.github.jangalinski.kobweb.tabler.components.renderNavbar

/**
 * Navbar block for the Tabler page shell.
 *
 * This represents the preview's `<!-- BEGIN NAVBAR -->` region and renders
 * the primary link and dropdown navigation. It remains separate from the
 * sidebar and route content in the Kobweb layout.
 */
@Composable
fun TablerNavbar(navigation: TablerNavigation.HeaderNavigation) {
  navigation.renderNavbar()
}
