package kson

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JsonObjectTest {

  @Test
  fun `should decode a string`() {
    val result = JsonObject.decodeFrom("""
      {
        "key": "value"
      }
    """.trimIndent()).mapToObject(Fuzz::class)

    assertEquals(Fuzz("value"), result)
  }

  data class Fuzz(
    val key: String
  )
}