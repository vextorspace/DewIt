package model

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class ItemReconstitutesFromJsonTest {

    @Test
    fun `empty json string returns null`() {
        // Given
        val itemJson = "[]"

        // When
        val item = Item.fromJson(itemJson)

        // Then
        item shouldBe null
    }
}