package com.github.jangalinski.kobweb.tabler.gradle.detekt

import dev.detekt.api.Config
import dev.detekt.api.RuleName
import dev.detekt.api.RuleSet
import dev.detekt.api.RuleSetId
import dev.detekt.api.RuleSetProvider

/**
 * Exposes the custom Kobweb Tabler detekt rules to the detekt runtime.
 *
 * The rules are intentionally tiny and repo-specific so they can enforce Kobweb conventions
 * that the framework itself does not validate.
 */
class KobwebTablerRuleSetProvider : RuleSetProvider {

  override val ruleSetId: RuleSetId = RuleSetId("kobweb-tabler")

  override fun instance(): RuleSet = RuleSet(
    ruleSetId,
    mapOf(
      RuleName("FoundationComposeOnlyRule") to { config: Config -> FoundationComposeOnlyRule(config) },
    ),
  )
}
