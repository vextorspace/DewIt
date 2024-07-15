package model

import model.visitors.ItemVisitorAction

class ItemVisitorActionFindItemContaining(val content: String) : ItemVisitorAction {

    val items = mutableListOf<Item>()

    override fun accept(item: Item) {
        if (item.content.contains(content)) {
            items.add(item)
        }
    }
}
