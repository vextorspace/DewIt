package viewmodel

import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import model.Item
import kotlin.test.Test

class DewItViewModelFindsByIdTest {

    @Test
    fun `DewItViewModel finds item by id`() {
        // Given
        val item1 = Item("item1", id = "::ITEM1 ID::")
        val idToFind = "::ITEM2 ID::"
        val item2 = Item("item2", id = idToFind)
        item1.add(item2)
        val model = DewItViewModel(listOf(item1))

        // when
        val item = model.findItemById(idToFind)

        // Then
        item.shouldNotBeNull()
            .shouldBeEqual(item2)
    }
}