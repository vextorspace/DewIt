package model

class ItemVisitorCollector : ItemVisitorAction {
    val items = mutableListOf<Item>()

    override fun accept(item: Item) {
        items.add(item)
    }

}
