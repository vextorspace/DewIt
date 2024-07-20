package model.visitors

import io.kotest.matchers.collections.shouldContainExactly
import model.ActionType
import model.Item
import model.ItemWorkflow
import kotlin.test.Test

class ItemFindsFirstParentWithWorkflowTest {

    @Test
    fun `if item has workflows they are used`() {
        val item = Item()
        val itemWorkflow = ItemWorkflow("::SOURCE::", "::DESTINATION::", ActionType.COPY)
        item.addWorkflow(itemWorkflow)

        val workflows: List<ItemWorkflow> = item.findFirstWorkflows()

        workflows.shouldContainExactly(itemWorkflow)
    }

}