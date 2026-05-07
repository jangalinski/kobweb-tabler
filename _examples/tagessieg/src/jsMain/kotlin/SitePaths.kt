package net.janhoo.kotlin.kobweb.tabler.example.tagessieg

import com.varabyte.kobweb.navigation.BasePath

/**
 * Builds a browser path that respects the Kobweb base path.
 */
fun siteHref(path: String): String = BasePath.prependTo(path)
