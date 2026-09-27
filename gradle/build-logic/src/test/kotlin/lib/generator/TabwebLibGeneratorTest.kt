package com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib.generator

import com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib.model.ColorsModel
import org.junit.jupiter.api.Test


class TabwebLibGeneratorTest {

  @Test
  fun name() {
    val generator = TabwebLibGenerator(ColorsModel.load())

    val files = generator.invoke()

    files.forEach {
      println(it.code)
    }

  }
}
