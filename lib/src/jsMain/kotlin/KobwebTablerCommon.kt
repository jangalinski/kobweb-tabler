package com.github.jangalinski.kobweb.tabler

data object KobwebTablerCommon {

  fun icon(name: String) = IconName {
    name
  }
}


fun interface Value<T> {
  fun value(): T
}

fun interface IconName : Value<String>

enum class IconNames(private val value: String) : IconName {
  BRAND_GITHUB("brand-github"),

  Activity("activity"),
  Airplay("airplay"),
  AlertCircle("alert-circle"),
  AlertOctagon("alert-octagon"),
  AlertTriangle("alert-triangle"),
  AlignCenter("align-center"),
  AlignJustified("align-justified"),
  AlignLeft("align-left"),
  AlignRight("align-right"),
  Anchor("anchor"),
  Aperture("aperture"),
  Archive("archive"),
  ArrowDownCircle("arrow-down-circle"),
  ArrowDownLeft("arrow-down-left"),
  ArrowDownRight("arrow-down-right"),
  ArrowDown("arrow-down"),
  ArrowLeftCircle("arrow-left-circle"),
  ArrowLeft("arrow-left"),
  ;

  override fun value(): String = value
}
