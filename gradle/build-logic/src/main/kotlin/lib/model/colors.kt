package com.github.jangalinski.tabweb.gradle.buildlogic.lib.model

import com.github.jangalinski.tabweb.gradle.buildlogic.BuildLogic.loadResource
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


sealed interface TablerColorModel : TabwebModel {
  val name: String
  val value: String
  val short: String
  val enumName: String get() = name.uppercase().replace("-", "_").replace(" ", "_")


  @Serializable
  data class Base(
    override val name: String,
    override val short: String,
    override val value: String = name.lowercase(),
    val hasLightMode: Boolean = true
  ) : TablerColorModel

  @Serializable
  data class Semantic(
    override val name: String,
    override val short: String,
    override val value: String = name.lowercase(),
    val hasLightMode: Boolean = true
  ) : TablerColorModel


  @Serializable
  data class Gray(
    val occupance: Int
  ) : TablerColorModel {
    override val name: String = "Gray $occupance"
    override val short: String = "$occupance"
    override val value: String = "gray-$occupance"
  }

  @Serializable
  data class Social(
    override val name: String,
    override val short: String,
    override val value: String = name.lowercase(),
    val icon : String = "brand-$value"
  ) : TablerColorModel

}

@Serializable
data class ColorsModel(
  val base: List<TablerColorModel.Base>,
  val gray: List<TablerColorModel.Gray>,
  val semantic: List<TablerColorModel.Semantic>,
  val social: List<TablerColorModel.Social>,
) : TabwebModel {

  companion object {
    fun load(resource: String = "tabler/colors.json"): ColorsModel {
      return loadModelFromJson(resource)
    }
  }

}

@OptIn(InternalSerializationApi::class)
inline fun <reified T : TabwebModel> loadModelsFromJson(jsonResource: String): List<T> = Json.decodeFromString(
  ListSerializer(T::class.serializer()),
  loadResource(jsonResource)
)

@OptIn(InternalSerializationApi::class)
inline fun <reified T : TabwebModel> loadModelFromJson(jsonResource: String): T = Json.decodeFromString(
  T::class.serializer(),
  loadResource(jsonResource)
)
