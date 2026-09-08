package com.github.jangalinski.kobweb.tabler.styles

/**
 * Responsive width presets for blocks in the Tabler grid.
 */
enum class GridWidth(val classNames: String) {
  FULL(ClassNames.col12),
  HALF("col-12 col-sm-6"),
  THIRD("col-12 col-lg-4"),
  QUARTER(ClassNames.colSm6Lg3)
}
