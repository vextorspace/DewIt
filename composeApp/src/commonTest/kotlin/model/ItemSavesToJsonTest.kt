package model

import io.kotest.assertions.json.shouldEqualJson
import kotlin.test.Test

class ItemSavesToJsonTest {

    @Test
    fun `item saves to json`() {
        // Given
        val content = "::ITEM CONTENT::"
        val id = "::SOME_UUID::"
        val item = Item(content, mutableListOf(), id)

        // When
        val itemJson = item.toJson()

        // Then
        val expectedJson = """
            {
                "content": "$content",
                "id": "$id"
            }
        """.trimIndent()
        itemJson shouldEqualJson expectedJson
    }
}