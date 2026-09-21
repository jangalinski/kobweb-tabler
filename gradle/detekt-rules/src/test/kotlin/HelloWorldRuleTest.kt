package com.github.jangalinski.kobweb.tabler.gradle.detekt

import dev.detekt.api.Config
import dev.detekt.test.lint
import kotlin.test.Test
import kotlin.test.assertEquals

class HelloWorldRuleTest {

  @Test
  fun `reports a hello world function`() {
    val findings = HelloWorldRule(Config.empty).lint("fun helloWorld() = Unit")

    assertEquals(1, findings.size)
  }

  @Test
  fun `ignores other functions`() {
    val findings = HelloWorldRule(Config.empty).lint("fun greeting() = Unit")

    assertEquals(0, findings.size)
  }
}
