package model

interface ItemVisitorAction {
    fun accept(item: Item)
}
