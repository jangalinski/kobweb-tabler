package com.github.jangalinski.kobweb.tabler._foundation.compose

import kotlinx.browser.document

/**
 * Finds a browser DOM element by its document id without exposing a W3C type to component code.
 *
 * @param id id of the DOM element to find.
 * @return the matching element, or `null` when no matching element exists.
 */
internal fun documentElementById(id: String): dynamic = document.getElementById(id)
