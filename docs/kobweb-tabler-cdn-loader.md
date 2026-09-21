# Kobweb Tabler CDN Loading Strategy

This document describes a practical approach for using CDN assets in a Kobweb-based Tabler library or application.

## Goals

- keep **Tabler core assets** globally available
- load **optional plugin assets** only when a page or component actually needs them
- expose the **current Tabler version at runtime**, even if the version is defined in Gradle

This is usually the best trade-off between simplicity, maintainability, and performance.

---

## Recommended strategy

### Load globally
Use `build.gradle.kts` to load assets that are needed on most or all pages:

- Tabler core CSS
- Tabler icon CSS
- optionally `tabler.min.js`

### Load on demand
Load heavier or optional plugins only when needed:

- FullCalendar
- ApexCharts
- other Tabler plugin libraries

This avoids unnecessary network requests, parsing, and script execution on pages that do not use those plugins.

---

## 1. Keep Tabler core assets global

Example `build.gradle.kts` setup:

```kotlin
kobweb {
  library {
    index {
      head.add {
        style {
          importCss(
            url = "https://cdn.jsdelivr.net/npm/@tabler/core@<TABLER_VERSION>/dist/css/tabler.min.css",
            layerName = "kobweb-tabler"
          )
          importCss(
            url = "https://cdn.jsdelivr.net/npm/@tabler/icons-webfont@<ICONS_VERSION>/dist/tabler-icons.min.css",
            layerName = "kobweb-tabler"
          )
        }
        script {
          src = "https://cdn.jsdelivr.net/npm/@tabler/core@<TABLER_VERSION>/dist/js/tabler.min.js"
        }
      }
    }
  }
}
```

Replace:

- `<TABLER_VERSION>` with your Tabler version
- `<ICONS_VERSION>` with your icon version

---

## 2. Add a reusable CDN loader

Create a file such as `CdnLoader.kt`.

This utility:

- loads styles only once
- loads scripts only once
- waits until scripts are actually ready
- can be reused by multiple plugin wrappers

```kotlin
package your.package.assets

import kotlinx.browser.document
import org.w3c.dom.HTMLLinkElement
import org.w3c.dom.HTMLScriptElement
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object CdnLoader {
    private val loadedStyles = mutableSetOf<String>()
    private val loadedScripts = mutableSetOf<String>()

    fun loadStyleOnce(href: String) {
        if (href in loadedStyles) return

        if (document.querySelector("link[href='$href']") != null) {
            loadedStyles += href
            return
        }

        val link = document.createElement("link") as HTMLLinkElement
        link.rel = "stylesheet"
        link.href = href
        document.head!!.appendChild(link)

        loadedStyles += href
    }

    suspend fun loadScriptOnce(src: String) {
        if (src in loadedScripts) return

        if (document.querySelector("script[src='$src']") != null) {
            loadedScripts += src
            return
        }

        suspendCoroutine<Unit> { cont ->
            val script = document.createElement("script") as HTMLScriptElement
            script.src = src
            script.async = true

            script.onload = {
                loadedScripts += src
                cont.resume(Unit)
                null
            }

            script.onerror = {
                cont.resumeWithException(
                    IllegalStateException("Failed to load script: $src")
                )
                null
            }

            document.head!!.appendChild(script)
        }
    }
}
```

---

## 3. Add a plugin-specific loader

Create a dedicated loader per optional plugin.

Example: `TablerFullCalendarLoader.kt`

```kotlin
package your.package.assets

object TablerFullCalendarLoader {
    private var loaded = false

    suspend fun ensureLoaded() {
        if (loaded) return

        CdnLoader.loadStyleOnce(
            "https://cdn.jsdelivr.net/npm/fullcalendar@6.1.19/index.global.min.css"
        )
        CdnLoader.loadScriptOnce(
            "https://cdn.jsdelivr.net/npm/fullcalendar@6.1.19/index.global.min.js"
        )

        loaded = true
    }
}
```

If a plugin requires multiple scripts, load them in the correct order:

```kotlin
package your.package.assets

object SomePluginLoader {
    private var loaded = false

    suspend fun ensureLoaded() {
        if (loaded) return

        CdnLoader.loadStyleOnce("https://cdn.example.com/plugin.css")
        CdnLoader.loadScriptOnce("https://cdn.example.com/plugin-core.js")
        CdnLoader.loadScriptOnce("https://cdn.example.com/plugin-extension.js")

        loaded = true
    }
}
```

---

## 4. Use the loader inside a Kobweb page or component

This pattern waits for the assets before rendering the JS-backed widget.

```kotlin
package your.package.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import your.package.assets.TablerFullCalendarLoader

@Composable
fun CalendarPage() {
    var ready by remember { mutableStateOf(false) }
    var loadError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            TablerFullCalendarLoader.ensureLoaded()
            ready = true
        } catch (e: Throwable) {
            loadError = e.message ?: "Unknown loading error"
        }
    }

    when {
        loadError != null -> Div { Text("Calendar assets failed to load: $loadError") }
        !ready -> Div { Text("Loading calendar...") }
        else -> FullCalendarHost()
    }
}
```

---

## 5. Initialize the plugin when the DOM node exists

For JS plugins, the DOM element must exist before initialization.

Create a host composable such as `FullCalendarHost.kt`.

```kotlin
package your.package.calendar

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.dom.ref
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun FullCalendarHost() {
    Div(
        attrs = {
            ref { element: HTMLDivElement ->
                initFullCalendar(element)
                onDispose {
                    destroyFullCalendar(element)
                }
            }
        }
    )
}
```

---

## 6. Add minimal JS interop for FullCalendar

Create `FullCalendarInterop.kt`.

> Note: the exact global object shape depends on the CDN bundle you choose. The example below assumes the global bundle exposes `FullCalendar.Calendar`.

```kotlin
package your.package.calendar

import org.w3c.dom.HTMLDivElement
import kotlin.js.json

@JsName("FullCalendar")
external object FullCalendarGlobal {
    val Calendar: dynamic
}

private val calendarInstances = mutableMapOf<HTMLDivElement, dynamic>()

fun initFullCalendar(element: HTMLDivElement) {
    val calendar = FullCalendarGlobal.Calendar(
        element,
        json(
            "initialView" to "dayGridMonth"
        )
    )

    calendar.render()
    calendarInstances[element] = calendar
}

fun destroyFullCalendar(element: HTMLDivElement) {
    calendarInstances.remove(element)?.destroy()
}
```

---

## 7. Optional: wrap everything in a single lazy composable

This gives consumers a compact API.

```kotlin
package your.package.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import your.package.assets.TablerFullCalendarLoader

@Composable
fun LazyFullCalendar() {
    var ready by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            TablerFullCalendarLoader.ensureLoaded()
            ready = true
        } catch (t: Throwable) {
            error = t.message ?: "Failed to load FullCalendar"
        }
    }

    when {
        error != null -> Div { Text(error!!) }
        !ready -> Div { Text("Loading calendar...") }
        else -> FullCalendarHost()
    }
}
```

---

## 8. Export the Tabler version from Gradle to runtime

If your Tabler version is managed in Gradle, for example through a version catalog, you cannot access that value directly from browser runtime code.

The recommended approach is:

1. read the version in Gradle
2. generate a Kotlin file during the build
3. add that generated file to `jsMain`
4. use the generated constants from Kotlin/JS at runtime

### Why this is useful

This gives you a single source of truth:

- Gradle owns the version
- runtime code consumes the generated value
- no duplicated version string in Kotlin source files

### Example `build.gradle.kts`

```kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

val tablerCoreVersion = libs.versions.cdn.tabler.core.get()
val tablerIconsVersion = libs.versions.cdn.tabler.icons.get()

val generatedTablerRuntimeDir = layout.buildDirectory.dir("generated/kobwebTablerRuntime/kotlin")

val generateTablerRuntimeInfo by tasks.registering {
    outputs.dir(generatedTablerRuntimeDir)

    doLast {
        val outputFile = generatedTablerRuntimeDir.get()
            .file("com/github/jangalinski/kobweb/tabler/runtime/KobwebTablerRuntimeInfo.kt")
            .asFile

        outputFile.parentFile.mkdirs()
        outputFile.writeText(
            """
            package com.github.jangalinski.kobweb.tabler.runtime

            object KobwebTablerRuntimeInfo {
                const val TABLER_CORE_VERSION = "$tablerCoreVersion"
                const val TABLER_ICONS_VERSION = "$tablerIconsVersion"
            }
            """.trimIndent()
        )
    }
}

kotlin {
    sourceSets {
        jsMain {
            kotlin.srcDir(generatedTablerRuntimeDir)
        }
    }
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    dependsOn(generateTablerRuntimeInfo)
}
```

### Generated runtime object

The generated file will look like this:

```kotlin
package com.github.jangalinski.kobweb.tabler.runtime

object KobwebTablerRuntimeInfo {
    const val TABLER_CORE_VERSION = "1.5.1"
    const val TABLER_ICONS_VERSION = "3.34.1"
}
```

You should not maintain that generated file manually. It is just the output of the Gradle task.

---

## 9. Use the exported version in runtime loaders

Now your lazy loaders can build their CDN URLs from the generated runtime constants.

```kotlin
package your.package.assets

import com.github.jangalinski.kobweb.tabler.runtime.KobwebTablerRuntimeInfo

object TablerFullCalendarLoader {
    private var loaded = false

    suspend fun ensureLoaded() {
        if (loaded) return

        val tablerVersion = KobwebTablerRuntimeInfo.TABLER_CORE_VERSION

        CdnLoader.loadStyleOnce(
            "https://cdn.jsdelivr.net/npm/@tabler/core@$tablerVersion/dist/libs/fullcalendar/index.global.min.css"
        )
        CdnLoader.loadScriptOnce(
            "https://cdn.jsdelivr.net/npm/@tabler/core@$tablerVersion/dist/libs/fullcalendar/index.global.min.js"
        )

        loaded = true
    }
}
```

This keeps the runtime code aligned with the Gradle-managed Tabler version.

---

## 10. Optional: expose runtime version info on `window`

If you want to inspect the version from browser dev tools or consume it from plain JavaScript, publish it on `window`.

```kotlin
package your.package.runtime

import com.github.jangalinski.kobweb.tabler.runtime.KobwebTablerRuntimeInfo
import kotlinx.browser.window
import kotlin.js.json

fun publishTablerRuntimeInfo() {
    window.asDynamic().__kobwebTabler = json(
        "coreVersion" to KobwebTablerRuntimeInfo.TABLER_CORE_VERSION,
        "iconsVersion" to KobwebTablerRuntimeInfo.TABLER_ICONS_VERSION
    )
}
```

Call this once during application startup if you need that global visibility.

Then in the browser console:

```javascript
window.__kobwebTabler.coreVersion
```

---

## 11. Why this approach works well

### Advantages

- small initial page payload
- optional plugins stay optional
- easy to reuse across pages
- no duplicate asset injection
- Gradle remains the single source of truth for versions
- runtime code can still build CDN URLs dynamically

### Good fit for a library

If you maintain a library such as `kobweb-tabler`, this pattern scales well:

- keep `core` assets global
- provide a loader per optional plugin
- generate one runtime info object from Gradle
- expose simple wrappers like `LazyFullCalendar()`

This avoids turning the base library into one large bundle that always loads everything.

---

## 12. Recommended project structure

A possible structure:

```text
src/jsMain/kotlin/
  your/package/assets/
    CdnLoader.kt
    TablerFullCalendarLoader.kt
    TablerApexChartsLoader.kt
  your/package/calendar/
    FullCalendarHost.kt
    FullCalendarInterop.kt
    LazyFullCalendar.kt
  your/package/runtime/
    BrowserRuntimeInfo.kt
build/generated/kobwebTablerRuntime/kotlin/
  com/github/jangalinski/kobweb/tabler/runtime/
    KobwebTablerRuntimeInfo.kt
```

If your library grows, consider splitting plugin wrappers into separate modules:

- `kobweb-tabler-core`
- `kobweb-tabler-fullcalendar`
- `kobweb-tabler-apexcharts`

That gives consumers even more control over what they include.

---

## 13. Things to watch out for

### Script order matters
If one script depends on another, load the dependency first.

### CSS usually does not need cleanup
It is generally fine to leave a dynamically inserted stylesheet in the document.

### Destroy JS instances on dispose
The most important cleanup is usually the plugin instance itself, not the `<script>` or `<link>` element.

### Guard against double loading
Always track what has already been loaded.

### CDN bundle format matters
Some libraries expose globals, others are ESM-only. The examples in this document assume a browser-global bundle.

### Gradle values are not browser values
Anything from `libs.versions`, `gradle.properties`, or Gradle tasks must be exported explicitly if runtime code needs it.

---

## Summary

Use this rule of thumb:

- **Global in Gradle:** Tabler core assets used across the whole site
- **Lazy in Kotlin/JS:** optional plugin assets used only on specific pages
- **Generated runtime constants:** version information that must be shared between Gradle and browser code

For plugins like **FullCalendar**, lazy loading is usually the better choice.

For values like the current **Tabler version**, generated runtime Kotlin constants are usually the cleanest solution.

---

## Copy checklist

When copying this into a real project, update:

- package names
- CDN versions
- plugin URLs
- JS interop object names if the chosen CDN bundle differs
- generated file package path
- calendar options in `initFullCalendar`

---

## Minimal adoption path

If you want the smallest possible rollout:

1. keep current Tabler core loading in `build.gradle.kts`
2. add `CdnLoader.kt`
3. add a generated `KobwebTablerRuntimeInfo.kt`
4. add `TablerFullCalendarLoader.kt`
5. call `ensureLoaded()` from `LaunchedEffect`
6. initialize the calendar only after loading succeeds

That is already a solid production-friendly setup.

