package model.visitors

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldNotHaveSameHashCodeAs
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

    @Test
    fun `returned workflows are not the original list`() {
        val item = Item()
        val itemWorkflow = ItemWorkflow("::SOURCE::", "::DESTINATION::", ActionType.COPY)
        item.addWorkflow(itemWorkflow)
        val workflowNotThere = ItemWorkflow("::OTHER SOURCE::","::OTHER DESTINATION::", ActionType.COPY)

        val workflows = item.findFirstWorkflows()

        item.addWorkflow(workflowNotThere)

        workflows.shouldNotContain(workflowNotThere)

    }


    @Test
    fun `if no workflows and grandparent has workflows finds those`() {
        val grandparent = Item("grandparent")
        val parent = Item("parent")
        val child = Item("child")
        val workflow = ItemWorkflow("::SOURCE::", "::DESTINATION::", ActionType.COPY)

        grandparent.add(parent)
        grandparent.addWorkflow(workflow)
        parent.add(child)

        child.findFirstWorkflows().shouldContainExactly(workflow)
        parent.findFirstWorkflows().shouldContainExactly(workflow)
        grandparent.findFirstWorkflows().shouldContainExactly(workflow)
    }



}