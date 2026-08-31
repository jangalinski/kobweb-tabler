package net.janhoo.kotlin.kobweb.tabler.example.tagessieg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.KobwebApp
import kotlinx.browser.document
import net.janhoo.kotlin.kobweb.tabler.models.Image.InlineSvg

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
  LaunchedEffect(Unit) {
    document.body?.className = "bg-body"
  }

  KobwebApp {
    content()
  }
}

data object SiteConfig {

  /**
   * Inline the brand mark so `kobweb run` and exported output render the same asset.
   */
  val LOGO = InlineSvg(
    svg = KO2_LOGO_SVG,
    altText = "KO2 Tagessieg Logo"
  )
}

private val KO2_LOGO_SVG = """
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 256" role="img" aria-label="KO2 Tagessieg logo">
  <rect width="256" height="256" rx="56" fill="#0b0f14"/>
  <circle cx="128" cy="128" r="92" fill="none" stroke="#efaf1f" stroke-width="12"/>
  <circle cx="128" cy="128" r="72" fill="none" stroke="#0f6f20" stroke-width="10" stroke-dasharray="10 10"/>
  <text x="128" y="150" text-anchor="middle" font-family="Arial, Helvetica, sans-serif" font-size="62" font-weight="700" letter-spacing="2" fill="#ffffff">KO2</text>
</svg>
""".trimIndent()
