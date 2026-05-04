package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

@Composable
fun TablerPage(
  modifier: Modifier = Modifier,
  header: @Composable () -> Unit = {},
  footer: @Composable () -> Unit = {},
  content: @Composable ColumnScope.() -> Unit,
) {
  Box(modifier = modifier.classNames("page")) {
    Column(modifier = Modifier.classNames("page-wrapper")) {
      header()
      Column(
        modifier = Modifier.classNames("page-body"),
      ) {
        Column(modifier = Modifier.classNames("container-xl")) {
          content()
        }
      }
      footer()
    }
  }
}
