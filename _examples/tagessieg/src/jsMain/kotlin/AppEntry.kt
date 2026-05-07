package net.janhoo.kotlin.kobweb.tabler.example.tagessieg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.KobwebApp
import kotlinx.browser.document
import net.janhoo.kotlin.kobweb.tabler.models.Image.ImageResource

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

  val LOGO = ImageResource(
    resource = "ko2-logo.svg",
    altText = "KO2 Tagessieg Logo"
  )
}

