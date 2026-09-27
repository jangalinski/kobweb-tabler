package com.github.jangalinski.kobweb.tabler._foundation

import com.varabyte.kobweb.compose.ui.Modifier

/**
 * A sealed interface representing an image, which can be either an inline SVG, an image resource, or none.
 */
sealed interface Image {

  val modifier: Modifier get() = Modifier
  val altText: String? get() = null

  /**
   * A singleton object representing the absence of an image.
   */
  data object None : Image

  /**
   * A data class representing an inline SVG image.
   */
  data class InlineSvg(val svg: String, override val modifier: Modifier = Modifier, override val altText: String? = null) : Image

  /**
   * A data class representing an image resource.
   */
  data class Resource(
    val url: Url,
    override val modifier: Modifier = Modifier,
    override val altText: String? = null
  ) : Image

}
