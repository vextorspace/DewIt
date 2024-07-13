package viewmodel

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import model.Item
import kotlin.test.Test

class DewItViewModelCanModifyModelTest {

    @Test
    fun `add item to list`() {
        // Given
        val viewModel = DewItViewModel(item = Item())
        val item = Item("Item 1")

        // When
        viewModel.addItem(item)

        // Then
        viewModel.itemsState.value shouldHaveSize 1
        viewModel.itemsState.value[0] shouldBe item
    }

    @Test
    fun `cannot add same item twice`() {
        //Given item in model
        val item = Item("Item 1")
        val viewModel = DewItViewModel(listOf(item))

        //When item is added again
        viewModel.addItem(item)

        //Then the item is only in the list once
        viewModel.itemsState.value shouldHaveSize 1
    }
}