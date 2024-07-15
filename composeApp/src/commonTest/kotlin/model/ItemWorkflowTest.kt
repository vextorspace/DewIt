package model

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import viewmodel.DewItViewModel
import kotlin.test.Test

class ItemWorkflowTest {

    @Test
    fun `ItemWorkflow contains actionType defaults to Copy`() {
        // Given
        val source = Item()
        val destinationItem = Item()
        val itemWorkflow = ItemWorkflow(source.id, destinationItem.id)

        // When
        val actionType = itemWorkflow.actionType

        // Then
        actionType.shouldBeInstanceOf<ActionType>()
            .shouldBe(ActionType.COPY)
    }

    @Test
    fun `When executing the copy action on an ItemWorkflow it should put a copy of the item in the destination`() {
        // Given
        val parent = Item("parent")
        val child = Item("child")
        val copyToParent = Item("copy to parent")
        parent.add(child)
        val model = DewItViewModel(listOf(parent, copyToParent))

        val itemWorkflow = ItemWorkflow(
            source = parent.id,
            destination = copyToParent.id,
            actionType = ActionType.COPY
        )

        // When
        itemWorkflow.execute(child, model)

        // Then
        copyToParent.subItems.shouldContainExactly(child)
        parent.subItems.shouldContainExactly(child)
    }

    @Test
    fun `Cannot copy item to itself`() {
        // Given
        val parent = Item("parent")
        val child = Item("child")
        parent.add(child)
        val model = DewItViewModel(listOf(parent))

        val itemWorkflow = ItemWorkflow(parent.id, child.id, ActionType.COPY,)

        // When
        itemWorkflow.execute(child, model)

        // Then
        child.subItems.shouldBeEmpty()
    }

    @Test
    fun `Copy does nothing if source is destination`() {
        // Given
        val parent = Item("parent")
        val child = Item("child")
        parent.add(child)
        val model = DewItViewModel(listOf(parent))

        val itemWorkflow = ItemWorkflow(parent.id, parent.id, ActionType.COPY,)

        // When
        itemWorkflow.execute(child, model)

        // Then
        parent.subItems.shouldContainExactly(child)
    }

    @Test
    fun `When executing the move action on an ItemWorkflow it should remove from parent and add to destination`() {
        // Given
        val parent = Item("parent")
        val child = Item("child")
        val copyToParent = Item("copy to parent")
        parent.add(child)
        copyToParent.add(child)

        val model = DewItViewModel(listOf(parent, copyToParent))
        val itemWorkflow = ItemWorkflow(parent.id, copyToParent.id, ActionType.MOVE,)

        // When
        itemWorkflow.execute(child, model)

        // Then
        copyToParent.subItems.shouldContainExactly(child)
        parent.subItems.shouldBeEmpty()
    }

    @Test
    fun `Move does nothing if destination is the same as the item`() {
        // Given
        val parent = Item("parent")
        val child = Item("child")
        parent.add(child)
        val model = DewItViewModel(listOf(parent))

        val itemWorkflow = ItemWorkflow(parent.id, child.id, ActionType.MOVE,)

        // When
        itemWorkflow.execute(child, model)

        // Then
        parent.subItems.shouldContainExactly(child)
        child.subItems.shouldBeEmpty()
    }

    @Test
    fun `Move does nothing if destination is source`() {
        // Given
        val parent = Item("parent")
        val child = Item("child")
        parent.add(child)
        val model = DewItViewModel(listOf(parent))

        val itemWorkflow = ItemWorkflow(parent.id, parent.id, ActionType.MOVE,)

        // When
        itemWorkflow.execute(child, model)

        // Then
        parent.subItems.shouldContainExactly(child)
    }
}