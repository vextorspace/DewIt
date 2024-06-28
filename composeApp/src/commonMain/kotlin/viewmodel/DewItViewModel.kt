package viewmodel

import model.Item

class DewItViewModel(initialItems: List<Item>) {
    private val _items = initialItems.toMutableList()

    val items: List<Item>
        get() = _items

    fun add(item: Item) {
        _items.add(item)
    }


    companion object {
        fun emptyModel(): DewItViewModel {
            return DewItViewModel(listOf())
        }
    }
}
