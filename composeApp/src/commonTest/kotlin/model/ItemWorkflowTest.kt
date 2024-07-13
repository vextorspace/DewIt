package model

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.test.Test

class ItemWorkflowTest {

    @Test
    fun `ItemWorkflow contains destination item`() {
        // Given
        val item = Item()
        val destinationItem = Item()
        val itemWorkflow = ItemWorkflow(item, destinationItem)

        // When
        val destination = itemWorkflow.item

        // Then
        destination.shouldBeInstanceOf<Item>()
    }

    @Test
    fun `ItemWorkflow contains actionType defaults to Copy`() {
        // Given
        val item = Item()
        val destinationItem = Item()
        val itemWorkflow = ItemWorkflow(item, destinationItem)

        // When
        val actionType = itemWorkflow.actionType

        // Then
        actionType.shouldBeInstanceOf<ActionType>()

        actionType.shouldBe(ActionType.Copy)
    }

    @Test
    fun `When executing the copy action on an ItemWorkflow it should put a copy of the item in the destination`() {
        // Given
        val item = Item("parent")
        val child = Item("child")
        val copyToParent = Item("copy to parent")
        item.add(child)

        val itemWorkflow = ItemWorkflow(child, copyToParent, ActionType.Copy)

        // When
        itemWorkflow.execute()

        // Then
        copyToParent.subItems.shouldContainExactly(child)
        item.subItems.shouldContainExactly(child)
    }
}