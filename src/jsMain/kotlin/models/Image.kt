package io.github.jangalinski.kotlin.kobweb.tabler.models

import com.varabyte.kobweb.compose.ui.Modifier

sealed interface Image {

  val modifier: Modifier
  val altText: String?


  data object None : Image {
    override val modifier: Modifier = Modifier
    override val altText: String? = null
  }

  data class InlineSvg(val svg: String, override val modifier: Modifier = Modifier, override val altText: String? = null) : Image

  data class ImageResource(val resource: String, override val modifier: Modifier = Modifier, override val altText: String? = null) : Image

}
