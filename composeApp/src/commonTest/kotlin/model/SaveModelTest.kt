package model

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import viewmodel.DewItViewModel
import kotlin.test.Test

class SaveModelTest {

    @Test
    fun `extracts flyweight model from DewItViewModel`() {
        // Given
        val item1 = Item("Item 1")
        val item2 = Item("Item 2")
        val child1 = Item("Child 1")
        item1.add(child1)
        val items = listOf(item1, item2)
        val viewModel = DewItViewModel(items)

        // When
        val saveModel = viewModel.saveModel

        // Then
        saveModel.items.shouldContainExactlyInAnyOrder(viewModel.item.item, item1, item2, child1)

        saveModel.model.id shouldBe viewModel.item.item.id
        saveModel.model.subItems.map { it.id } shouldBe listOf(item1.id, item2.id)
        saveModel.model.subItems.filter { it.subItems.isNotEmpty() }.map { it.subItems.first().id } shouldBe listOf(child1.id)
    }

}