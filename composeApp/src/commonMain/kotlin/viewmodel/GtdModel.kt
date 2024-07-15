package viewmodel

import model.ActionType
import model.Item
import model.ItemWorkflow

object GtdModel {
    fun createModel(): DewItViewModel {
        val inbox = Item(
            content = "Inbox",
            subItems = listOf(
                Item("Review How You Use This App")
            )
        )

        val todo = Item(
            content = "Todo",
            subItems = listOf(
                Item("Read GTD Book"),
                Item("Do a Dump")
            )
        )
        val projects = Item(
            "Projects"
        )
        val someday = Item(
            "Someday Maybe"
        )
        val waiting = Item(
            "Waiting On"
        )
        val references = Item(
            "References"
        )
        val itemList = listOf(
            inbox,
            todo,
            projects,
            someday,
            waiting,
            references
        )

        inbox.addWorkflow(ItemWorkflow(inbox.id, todo.id, ActionType.MOVE))
        inbox.addWorkflow(ItemWorkflow(inbox.id, projects.id, ActionType.MOVE))
        inbox.addWorkflow(ItemWorkflow(inbox.id, someday.id, ActionType.MOVE))
        inbox.addWorkflow(ItemWorkflow(inbox.id, references.id, ActionType.MOVE))

        projects.addWorkflow(ItemWorkflow(projects.id, someday.id, ActionType.MOVE))
        projects.addWorkflow(ItemWorkflow(projects.id, waiting.id, ActionType.MOVE))
        projects.addWorkflow(ItemWorkflow(projects.id, todo.id, ActionType.COPY))


        val root = Item("Root")
        root.subItems.addAll(itemList)

        return DewItViewModel(root)
    }
}