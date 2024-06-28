package viewmodel

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainOnly
import model.Item
import kotlin.test.Test

class DewItViewModelTest {

    @Test
    fun `ViewModel emptyModel returns empty list`() {
        val viewModel = DewItViewModel.emptyModel()

        viewModel.items.shouldBeEmpty()
    }

    @Test
    fun `ViewModel can add item to list`() {
        val viewModel = DewItViewModel.emptyModel()
        val item = Item("New Item")

        viewModel.add(item)

        viewModel.items.shouldContainOnly(item)
    }

    @Test
    fun `ViewModel can subtract item from list`() {
        val viewModel = DewItViewModel.emptyModel()
        val item = Item("New Item")
        viewModel.add(item)

        viewModel.subtract(item).shouldBeTrue()

        viewModel.items.shouldBeEmpty()
    }

    @Test
    fun `ViewModel subtract returns false if item not in list`() {
        val viewModel = DewItViewModel.emptyModel()
        val item = Item("New Item")

        viewModel.subtract(item).shouldBeFalse()
    }

    @Test
    fun `ViewModel can set new list`() {
        val viewModel = DewItViewModel.emptyModel()
        val items = listOf(Item("New Item 1"), Item("New Item 2"))

        viewModel.setItems(items)

        viewModel.items.shouldContainOnly(items[0], items[1])
    }
}