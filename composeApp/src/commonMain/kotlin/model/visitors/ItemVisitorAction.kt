package model.visitors

import model.Item

interface ItemVisitorAction {
    fun accept(item: Item)
}
