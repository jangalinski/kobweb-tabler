package com.github.jangalinski.kobweb.tabler._foundation

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KImg
import com.varabyte.kobweb.compose.css.BackgroundImage
import com.varabyte.kobweb.compose.ui.Modifier

/**
 * A sealed interface representing an image, which can be either an inline SVG, an image resource, or none.
 */
sealed interface Image : Tabler.FoundationComponent {

  companion object {
    operator fun invoke(
      url: Url,
      modifier: Modifier = Modifier,
      altText: String? = null
    ): Image.Resource = Image.Resource(url, modifier, altText)

    operator fun invoke(
      url: String,
      modifier: Modifier = Modifier,
      altText: String? = null
    ): Image.Resource = Image.Resource(url, modifier, altText)
  }

  val modifier: Modifier get() = Modifier
  val altText: String? get() = null

  /**
   * A singleton object representing the absence of an image.
   */
  data object None : Image {
    @Composable
    override fun invoke(modifier: Modifier) {
      // No image to render
    }
  }

  /**
   * A data class representing an inline SVG image.
   */
  data class InlineSvg(val svg: String, override val modifier: Modifier = Modifier, override val altText: String? = null) : Image {
    @Composable
    override fun invoke(modifier: Modifier) {
      // Render the inline SVG using the provided modifier
      // This is a placeholder; actual rendering logic would depend on your UI framework
      // For example, you might use an HTML <svg> element or a Compose function to render the SVG
    }
  }

  /**
   * A data class representing an image resource.
   */
  data class Resource(
    val url: Url,
    override val modifier: Modifier = Modifier,
    override val altText: String? = null
  ) : Image {

    constructor(url: String, modifier: Modifier = Modifier, altText: String? = null) : this(Url(url), modifier, altText)

    @Composable
    override fun invoke(modifier: Modifier) {
      KImg(
        src = url.get(),
        alt = altText ?: "",
        modifier = modifier.then(this.modifier)
      )
    }

    val backgroundImage: BackgroundImage by lazy { BackgroundImage.of(url.cssUrl()) }
  }
}
