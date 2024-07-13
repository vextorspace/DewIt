package model

import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.test.Test

class ItemContainsListOfWorkflowsTest {

    @Test
    fun `item contains list of workflows`() {
        // Given
        val item = Item()

        // When
        val workflows = item.workflows

        // Then
        workflows.shouldBeInstanceOf<MutableList<ItemWorkflow>>()
    }
}