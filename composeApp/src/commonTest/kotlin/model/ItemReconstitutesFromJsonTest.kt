package model

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.test.Test

class ItemReconstitutesFromJsonTest {

    @Test
    fun `empty json string returns default item`() {
        // Given
        val itemJson = "{}"

        // When
        val item = Item.fromJson(itemJson)

        // Then
        item shouldNotBe null
    }

    @Test
    fun `Item reconstitutes from json`() {
        // Given
        val id = "::SOME_UUID::"
        val content = "::ITEM CONTENT::"
        val itemJson = """
            {
                "content": "$content",
                "id": "$id",
                "subItems": []
            }
        """

        // When
        val item = Item.fromJson(itemJson)

        // Then
        item shouldBe Item(content, mutableListOf(), id)
        item!!.subItems.shouldBeEmpty()
    }

}