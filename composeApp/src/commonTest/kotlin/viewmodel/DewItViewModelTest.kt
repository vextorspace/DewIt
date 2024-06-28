package viewmodel

import androidx.compose.runtime.MutableState
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import model.Item
import kotlin.test.Test

class DewItViewModelTest {

    @Test
    fun `DewItViewModel provides a mutable state of mutable list`() {
        // Given
        val viewModel = DewItViewModel()

        // When
        val itemsState = viewModel.itemsState

        // Then
        itemsState.shouldBeInstanceOf<MutableState<MutableList<Item>>>()
    }

    @Test
    fun `When DewItViewModel is created the mutable list is empty`() {
        // Given
        val viewModel = DewItViewModel()

        // When
        val items = viewModel.itemsState.value

        // Then
        items.shouldBeEmpty()
    }

    @Test
    fun `DewItViewModel constructed with list gives those items`() {
        // Given
        val item1 = Item("Item 1")
        val item2 = Item("Item 2")
        val items = listOf(item1, item2)

        // When
        val viewModel = DewItViewModel(items)

        // Then
        viewModel.itemsState.value
            .shouldContainExactly(item1, item2)
    }

    @Test
    fun `DewItViewModel reconstituted from an empty json string gives empty list`() {
        // Given
        val viewModelJson = "[]"

        // When
        val viewModel = DewItViewModel.fromJson(viewModelJson)

        // Then
        viewModel.itemsState.value.shouldBeEmpty()
    }

    @Test
    fun `DewItViewModel reconstituted with one item in list`() {
        // Given
        val viewModelJson = """
            [
                {
                    "content":"Inbox"
                    ,"subItems":[]
                }
            ]
            """.trimIndent()

        // When
        val viewModel = DewItViewModel.fromJson(viewModelJson)

        // Then
        val items = viewModel.itemsState.value
        items.shouldHaveSize(1)
        items[0].content.shouldBe("Inbox")
        items[0].subItems.shouldBeEmpty()
    }

    @Test
    fun `DewItViewModel reconstituted with two items in list`() {
        // Given
        val viewModelJson = """
            [
                {
                    "content":"Inbox"
                    ,"subItems":[
                        {
                            "content":"Review How You Use This App"
                            ,"subItems":[]
                            ,"id":"::CHILD_UUID::"
                        }
                        
                    ]
                    ,"id":"::PARENT_UUID::"
                },
                {
                    "content":"Todo"
                    ,"subItems":[]
                    ,"id":"::OTHER_UUID::"
                }
            ]
            """.trimIndent()

        // When
        val viewModel = DewItViewModel.fromJson(viewModelJson)

        // Then
        val items = viewModel.itemsState.value
        items.shouldHaveSize(2)
        items.shouldContainExactly(
            Item(
                "Inbox",
                mutableListOf(
                    Item("Review How You Use This App", id = "::CHILD_UUID::")
                ),
                "::PARENT_UUID::"
            ),
            Item("Todo", mutableListOf(), "::OTHER_UUID::")
        )
    }
}