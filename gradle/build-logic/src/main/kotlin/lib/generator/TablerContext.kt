package com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib.generator

import com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib.LIB_ROOT_PACKAGE
import com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib.model.ColorsModel
import com.squareup.kotlinpoet.ExperimentalKotlinPoetApi
import io.toolisticon.kotlin.generation.spi.KotlinCodeGenerationSpiRegistry
import io.toolisticon.kotlin.generation.spi.context.KotlinCodeGenerationContextBase

@ExperimentalKotlinPoetApi
class TablerContext(
  registry: KotlinCodeGenerationSpiRegistry,
  val colors: ColorsModel
) : KotlinCodeGenerationContextBase<TablerContext>(registry) {
  override val contextType = TablerContext::class

  val basePackage = LIB_ROOT_PACKAGE
}
