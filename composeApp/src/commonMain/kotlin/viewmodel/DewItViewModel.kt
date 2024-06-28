package viewmodel

import model.Item

class DewItViewModel(initialItems: List<Item>) {
    private val _items = initialItems.toMutableList()

    val items: List<Item>
        get() = _items

    fun setItems(items: List<Item>) {
        _items.clear()
        _items.addAll(items)
    }


    fun add(item: Item) {
        _items.add(item)
    }

    fun subtract(item: Item): Boolean {
        return _items.remove(item)
    }


    companion object {
        fun emptyModel(): DewItViewModel {
            return DewItViewModel(listOf())
        }
    }
}
