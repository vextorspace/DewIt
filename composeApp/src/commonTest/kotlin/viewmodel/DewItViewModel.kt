package viewmodel

import io.kotest.matchers.collections.shouldContainOnly
import model.Item
import kotlin.test.Test

class DewItViewModelTest {

    @Test
    fun `ViewModel can add item to list`() {
        val viewModel = DewItViewModel.emptyModel()
        val item = Item("New Item")

        viewModel.add(item)

        viewModel.items.shouldContainOnly(item)
    }
}