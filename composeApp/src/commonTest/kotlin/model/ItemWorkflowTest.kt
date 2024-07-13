package model

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.test.Test

class ItemWorkflowTest {

    @Test
    fun `ItemWorkflow contains destination item`() {
        // Given
        val destinationItem = Item()
        val itemWorkflow = ItemWorkflow(destinationItem)

        // When
        val destination = itemWorkflow.destination

        // Then
        destination.shouldBeInstanceOf<Item>()
    }

    @Test
    fun `ItemWorkflow contains actionType defaults to Copy`() {
        // Given
        val destinationItem = Item()
        val itemWorkflow = ItemWorkflow(destinationItem)

        // When
        val actionType = itemWorkflow.actionType

        // Then
        actionType.shouldBeInstanceOf<ActionType>()

        actionType.shouldBe(ActionType.Copy)
    }
}