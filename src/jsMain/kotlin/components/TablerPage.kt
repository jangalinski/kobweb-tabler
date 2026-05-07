package net.janhoo.kotlin.kobweb.tabler.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import net.janhoo.kotlin.kobweb.tabler.ComposableReceiver
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.containerXl
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.modifier
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.page
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.pageBody
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.pageHeader
import net.janhoo.kotlin.kobweb.tabler.styles.ClassNames.pageWrapper

/**
 * Wraps page content in the Tabler page shell. Tabler defaults to:
 *
 * * "page"
 * * navigation, either in header or aside
 * * "page-wrapper"
 *   * "page-header"
 *   * "page-body"
 * * footer
 */
@Composable
fun TablerPage(
  modifier: Modifier = Modifier,
  navigation: TablerNavigation = TablerNavigation.None,
  header: ComposableReceiver? = null,
  footer: ComposableReceiver? = null,
  body: ComposableReceiver,
) {
  @Composable
  fun pagePart(modifier: Modifier, block: ComposableReceiver) = Column(modifier) {
    Column(modifier = modifier) {
      Column(modifier = containerXl.modifier()) {
        block()
      }
    }
  }

  Box(modifier = modifier.then(page.modifier())) {
    navigation.render()

    Column(modifier = pageWrapper.modifier()) {
      header?.let { pagePart(pageHeader.modifier()) { it.invoke() } }

      body.let { pagePart(pageBody.modifier()) { it.invoke() } }

      // footer
      footer?.invoke()
    }
  }
}
