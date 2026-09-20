package com.github.jangalinski.kobweb.tabler.navbar

import com.github.jangalinski.kobweb.tabler.styles.CssClass
import com.github.jangalinski.kobweb.tabler.styles.LazyClassNameModifier
import com.varabyte.kobweb.compose.ui.Modifier

enum class TablerNavbarCss(val value: String, delegate: LazyClassNameModifier = LazyClassNameModifier(value)) : CssClass by delegate {
  NAVBAR("navbar"),
  NAVBAR_NAV("navbar-nav"),
  NAVBAR_NAV_ITEM("navbar-nav-item"),
  NAVBAR_EXPAND_MD("navbar-expand-md"),
  NAVBAR_BRAND("navbar-brand"),
  NAVBAR_BRAND_AUTODARK("navbar-brand-autodark"),
  ;
}
