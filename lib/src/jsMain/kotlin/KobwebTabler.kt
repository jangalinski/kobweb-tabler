package com.github.jangalinski.kobweb.tabler

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.navigation.BasePath

data object KobwebTabler {
  const val TABLER_LAYER = "kobweb-tabler"

  fun publicResourcePath(fileName: String) : String = if (fileName.startsWith("/"))
    BasePath.prependTo(fileName)
  else
    publicResourcePath("/$fileName")
}

typealias ComposableReceiver = @Composable () -> Unit
