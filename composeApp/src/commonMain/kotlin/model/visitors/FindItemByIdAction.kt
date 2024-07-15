package model.visitors

import model.Item

class FindItemByIdAction(val id: String): ItemVisitorAction {
    var item: Item? = null

    override fun accept(item: Item) {
        if (item.id == id) {
            this.item = item
        }
    }
}