package net.janhoo.kotlin.kobweb.tabler;

import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.layer.SilkLayer
import com.varabyte.kobweb.silk.style.layer.add
import net.janhoo.kotlin.kobweb.tabler.KobwebTabler.TABLER_LAYER

data object KobwebTabler {
  const val TABLER_LAYER = "kobweb-tabler"
}

@InitSilk
fun initBuildScriptLayers(ctx: InitSilkContext) {
  ctx.stylesheet.cssLayers.add(TABLER_LAYER, after = SilkLayer.BASE)
}
