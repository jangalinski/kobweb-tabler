package io.github.jangalinski.kotlin.kobweb.tabler

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.navigation.BasePath
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.layer.SilkLayer
import com.varabyte.kobweb.silk.style.layer.add
import org.w3c.files.File

data object KobwebTabler {
  const val TABLER_LAYER = "kobweb-tabler"

  fun publicResourcePath(fileName: String) : String = if (fileName.startsWith("/"))
    BasePath.prependTo(fileName)
  else
    publicResourcePath("/$fileName")
}

typealias ComposableReceiver = @Composable () -> Unit

@InitSilk
fun initBuildScriptLayers(ctx: InitSilkContext) {
  ctx.stylesheet.cssLayers.add(KobwebTabler.TABLER_LAYER, after = SilkLayer.BASE)
}
