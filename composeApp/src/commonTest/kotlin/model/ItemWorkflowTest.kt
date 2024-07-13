package model

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.test.Test

class ItemWorkflowTest {

    @Test
    fun `ItemWorkflow contains actionType defaults to Copy`() {
        // Given
        val item = Item()
        val source = Item()
        val destinationItem = Item()
        val itemWorkflow = ItemWorkflow(item, source, destinationItem)

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

        val itemWorkflow = ItemWorkflow(child, item, copyToParent, ActionType.Copy,)

        // When
        itemWorkflow.execute()

        // Then
        copyToParent.subItems.shouldContainExactly(child)
        item.subItems.shouldContainExactly(child)
    }

    @Test
    fun `Cannot copy item to itself`() {
        // Given
        val item = Item("parent")
        val child = Item("child")
        item.add(child)

        val itemWorkflow = ItemWorkflow(child, item, child, ActionType.Copy,)

        // When
        itemWorkflow.execute()

        // Then
        child.subItems.shouldBeEmpty()
    }

    @Test
    fun `When executing the move action on an ItemWorkflow it should remove from parent and add to destination`() {
        // Given
        val item = Item("parent")
        val child = Item("child")
        val copyToParent = Item("copy to parent")
        item.add(child)
        copyToParent.add(child)

        val itemWorkflow = ItemWorkflow(child, item, copyToParent, ActionType.MOVE,)

        // When
        itemWorkflow.execute()

        // Then
        copyToParent.subItems.shouldContainExactly(child)
        item.subItems.shouldBeEmpty()
    }
}