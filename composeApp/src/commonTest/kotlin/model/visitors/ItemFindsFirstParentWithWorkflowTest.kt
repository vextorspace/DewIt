package model.visitors

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldNotContain
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
        val pedigreeFinder = PedigreeFinder(item)

        val workflows: List<ItemWorkflow> = item.findFirstWorkflows(pedigreeFinder)

        workflows.shouldContainExactly(itemWorkflow)
    }

    @Test
    fun `returned workflows are not the original list`() {
        val item = Item()
        val itemWorkflow = ItemWorkflow("::SOURCE::", "::DESTINATION::", ActionType.COPY)
        item.addWorkflow(itemWorkflow)
        val workflowNotThere = ItemWorkflow("::OTHER SOURCE::","::OTHER DESTINATION::", ActionType.COPY)
        val pedigreeFinder = PedigreeFinder(item)

        val workflows = item.findFirstWorkflows(pedigreeFinder)

        item.addWorkflow(workflowNotThere)

        workflows.shouldNotContain(workflowNotThere)

    }

    @Test
    fun `if no workflowsfound empty returned`() {
        val grandparent = Item("grandparent")
        val parent = Item("parent")
        val child = Item("child")

        grandparent.add(parent)
        parent.add(child)
        val pedigreeFinder = PedigreeFinder(grandparent)

        child.findFirstWorkflows(pedigreeFinder).shouldBeEmpty()
        parent.findFirstWorkflows(pedigreeFinder).shouldBeEmpty()
        grandparent.findFirstWorkflows(pedigreeFinder).shouldBeEmpty()

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
        val pedigreeFinder = PedigreeFinder(grandparent)

        child.findFirstWorkflows(pedigreeFinder).shouldContainExactly(workflow)
        parent.findFirstWorkflows(pedigreeFinder).shouldContainExactly(workflow)
        grandparent.findFirstWorkflows(pedigreeFinder).shouldContainExactly(workflow)
    }



}