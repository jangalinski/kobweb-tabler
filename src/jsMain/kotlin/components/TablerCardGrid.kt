package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames

@Composable
fun TablerCardGrid(
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit,
) {
  Row(
    modifier = modifier.classNames("row", "row-deck", "row-cards", "g-3", "mb-4"),
    content = content,
  )
}
