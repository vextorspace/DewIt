package viewmodel

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import model.Item
import kotlin.test.Test

class DewItViewModelCanModifyModelTest {

    @Test
    fun `add item to list`() {
        // Given
        val viewModel = DewItViewModel()
        val item = Item("Item 1")

        // When
        viewModel.addItem(item)

        // Then
        viewModel.itemsState.value shouldHaveSize 1
        viewModel.itemsState.value[0] shouldBe item
    }
}