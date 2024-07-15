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

        val root = Item("Root")
        root.subItems.addAll(itemList)

        return DewItViewModel(root)
    }
}