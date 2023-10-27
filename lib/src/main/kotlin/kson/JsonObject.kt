package kson

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

class JsonObject {
  private val objects: MutableMap<String, String> = mutableMapOf()

  companion object {
    @JvmStatic
    fun decodeFrom(value: String): JsonObject {
      return JsonObject().of(Json.decodeFromString<Map<String, String>>(value))
    }
  }

  fun of(entries: Map<String, String>): JsonObject {
    return apply {
      this.objects.clear()
      this.objects.putAll(entries)
    }
  }

  fun put(key: String, value: String): JsonObject {
    objects[key] = value
    return this
  }

  fun encode(): String {
    return Json.encodeToString(objects)
  }

  fun <T : Any> mapToObject(clazz: KClass<T>) : T {
    val constructor = clazz.constructors.first()
    val args = constructor
      .parameters.associateWith { this.objects[it.name] }

    return constructor.callBy(args)
  }


  override fun toString(): String {
    return this.encode()
  }

}