package com.github.jangalinski.kobweb.tabler.gradle.detekt

import dev.detekt.api.Config
import dev.detekt.api.Entity
import dev.detekt.api.Finding
import dev.detekt.api.Rule
import org.jetbrains.kotlin.psi.KtNamedFunction

/**
 * A minimal custom rule proving that the Kobweb Tabler rule set is loaded by Detekt.
 *
 * The rule is intentionally unrelated to the conventions planned in issue #106. It reports
 * functions named `helloWorld`, which makes it deterministic to test without affecting the
 * library's production sources.
 */
class HelloWorldRule(config: Config) : Rule(
  config,
  "Functions named helloWorld are reserved for the custom Detekt rule smoke test.",
) {

  override fun visitNamedFunction(function: KtNamedFunction) {
    super.visitNamedFunction(function)

    if (function.name == "helloWorld") {
      report(Finding(Entity.atName(function), description))
    }
  }
}
