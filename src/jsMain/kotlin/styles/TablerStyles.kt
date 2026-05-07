package net.janhoo.kotlin.kobweb.tabler.styles

data object TablerStyles {
  /** Badge class with an optional contextual background variant. */
  fun badge(variantClass: String? = null): String = listOfNotNull(ClassNames.badge, variantClass).joinToString(" ")

  /** Progress bar element with an optional Tabler background class. */
  fun progressBar(variantClass: String? = null): String =
    listOfNotNull(ClassNames.progressBar, variantClass).joinToString(" ")
}
