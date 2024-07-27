package viewmodel

import androidx.compose.runtime.MutableState
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.types.shouldBeInstanceOf
import model.Item
import kotlin.test.Test

class DewItViewModelTest {

    @Test
    fun `DewItViewModel provides a mutable state of mutable list`() {
        // Given
        val viewModel = DewItViewModel(item = Item())

        // When
        val itemsState = viewModel.itemsState

        // Then
        itemsState.shouldBeInstanceOf<MutableState<MutableList<Item>>>()
    }

    @Test
    fun `When DewItViewModel is created the mutable list is empty`() {
        // Given
        val viewModel = DewItViewModel(item = Item())

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
            .shouldContainExactly(ViewItem(item1, viewModel.item), ViewItem(item2, viewModel.item))
    }

    @Test
    fun `DewItViewModel gets list of items`() {
        val item1 = Item("Item 1")
        val item2 = Item("Item 2")
        val items = listOf(item1, item2)
        val viewModel = DewItViewModel(items)

        // When
        val itemsList: List<Item> = viewModel.allItems()

        // Then
        val root = viewModel.item.item
        itemsList.shouldContainExactlyInAnyOrder(root, item1, item2)
    }
}