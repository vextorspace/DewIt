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
        val model = SaveModel(viewModel)

        // Then
        model.items.shouldContainExactlyInAnyOrder(viewModel.item.item, item1, item2, child1)

        model.model.id shouldBe viewModel.item.item.id
        model.model.subItems.map { it.id } shouldBe listOf(item1.id, item2.id)
        model.model.subItems.filter { it.subItems.isNotEmpty() }.map { it.subItems.first().id } shouldBe listOf(child1.id)
    }

}