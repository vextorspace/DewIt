package viewmodel

import model.Item

object GtdModel {
    fun createModel(): DewItViewModel {
        return DewItViewModel(createList())
    }

    fun createList(): MutableList<Item> {
        val itemList = listOf(
            Item(
                "Inbox",
                listOf(
                    Item("Review How You Use This App")
                )
            ),
            Item(
                "Todo",
                listOf(
                    Item("Read GTD Book"),
                    Item("Do a Dump")
                )
            ),
            Item(
                "Projects"
            ),
            Item(
                "Someday Maybe"
            ),
            Item(
                "Waiting On"
            ),
            Item(
                "References"
            )
        )

        return itemList.toMutableList()
    }
}