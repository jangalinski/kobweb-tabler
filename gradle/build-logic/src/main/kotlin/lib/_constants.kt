@file:OptIn(ExperimentalKotlinPoetApi::class)

package com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib

import com.squareup.kotlinpoet.ExperimentalKotlinPoetApi
import com.squareup.kotlinpoet.MemberName
import io.toolisticon.kotlin.generation.KotlinCodeGeneration.name.className

const val PKG_ROOT = "com.github.jangalinski.kobweb.tabler"
const val PKG_FOUNDATION = "com.github.jangalinski.kobweb.tabler._foundation"

val TABLER_COLOR = className(PKG_FOUNDATION, "Tabler")
  .nestedClass("Color")

val TYPE_INITIALS = className(PKG_FOUNDATION, "Initials")
val TYPE_ICON = className("$PKG_ROOT.icon", "Icon")
val TYPE_TABLER_ICON = className("$PKG_ROOT.icon", "TablerIcon")
val TYPE_CSS_CLASS = className("$PKG_FOUNDATION.css", "CssClass")
val MEMBER_CSS_CLASS = MemberName("$PKG_FOUNDATION.css", "cssClass")
