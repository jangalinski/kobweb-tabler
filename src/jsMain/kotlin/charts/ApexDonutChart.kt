package net.janhoo.kotlin.kobweb.tabler.charts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.Div
import kotlin.js.json
import kotlin.random.Random
import org.w3c.dom.Element

private external class ApexCharts(element: Element, options: dynamic) {
  fun render(): dynamic
}

/**
 * Small data point used by [ApexDonutChart].
 */
data class DonutSlice(
  val label: String,
  val value: Int,
)

/**
 * Renders a simple ApexCharts donut chart inside a Tabler-friendly container.
 *
 * The ApexCharts runtime is expected to be available globally through the library script layer.
 */
@Composable
fun ApexDonutChart(
  slices: List<DonutSlice>,
  modifier: Modifier = Modifier,
  heightPx: Int = 280,
) {
  val chartId = remember {
    "apex-donut-${Random.nextInt(Int.MAX_VALUE).toUInt()}"
  }

  Box(modifier = modifier.classNames("w-100")) {
    Div(
      attrs = {
        attr("id", chartId)
        attr("style", "width: 100%; height: ${heightPx}px;")
      },
    )
  }

  LaunchedEffect(chartId, slices) {
    val element = document.getElementById(chartId) ?: return@LaunchedEffect
    val labels = slices.map { it.label }
    val series = slices.map { it.value }
    val options = json(
      "chart" to json(
        "type" to "donut",
        "toolbar" to json("show" to false),
      ),
      "labels" to labels.toTypedArray(),
      "series" to series.toTypedArray(),
      "legend" to json("show" to true),
      "dataLabels" to json("enabled" to false),
      "stroke" to json("width" to 2),
      "plotOptions" to json(
        "pie" to json(
          "donut" to json(
            "size" to "70%",
          ),
        ),
      ),
    )

    ApexCharts(element, options).render()
  }
}
