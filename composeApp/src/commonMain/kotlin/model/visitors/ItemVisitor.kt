package model.visitors

import model.Item

class ItemVisitor(val action: ItemVisitorAction) {
    fun visit(item: Item) {
        action.accept(item)
        item.subItems.forEach { visit(it) }
    }
}
