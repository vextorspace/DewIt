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

    @Test
    fun `nested items save to json`() {
        // Given
        val content = "::ITEM CONTENT::"
        val id = "::SOME_UUID::"
        val subItemContent = "::SUB ITEM CONTENT::"
        val subItemId = "::SUB ITEM UUID::"
        val subItem = Item(subItemContent, mutableListOf(), subItemId)
        val item = Item(content, mutableListOf(subItem), id)

        // When
        val itemJson = item.toJson()

        // Then
        val expectedJson = """
            {
                "content": "$content",
                "id": "$id",
                "subItems": [
                    {
                        "content": "$subItemContent",
                        "id": "$subItemId"
                    }
                ]
            }
        """.trimIndent()
        itemJson shouldEqualJson expectedJson
    }
}