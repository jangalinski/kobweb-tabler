@file:OptIn(ExperimentalKotlinPoetApi::class)

package com.github.jangalinski.kobweb.tabler.gradle.buildlogic.lib

import com.squareup.kotlinpoet.ExperimentalKotlinPoetApi
import com.squareup.kotlinpoet.MemberName
import io.toolisticon.kotlin.generation.KotlinCodeGeneration.name.className

const val LIB_ROOT_PACKAGE = "com.github.jangalinski.kobweb.tabler"
const val LIB_FOUNDATION_PACKAGE = "com.github.jangalinski.kobweb.tabler._foundation"
const val LIB_GENERATED_PACKAGE = "com.github.jangalinski.kobweb.tabler.generated"

val TABLER_COLOR = className("com.github.jangalinski.kobweb.tabler._foundation", "Tabler").nestedClass("Color")

val TYPE_INITIALS = className("com.github.jangalinski.kobweb.tabler._foundation.model", "Initials")
val TYPE_ICON = className("com.github.jangalinski.kobweb.tabler.icon", "Icon")
val TYPE_TABLER_ICON = className("com.github.jangalinski.kobweb.tabler.icon", "TablerIcon")
val TYPE_CSS_CLASS = className("com.github.jangalinski.kobweb.tabler._foundation.css", "CssClass")
val MEMBER_CSS_CLASS = MemberName("com.github.jangalinski.kobweb.tabler._foundation.css", "cssClass")
