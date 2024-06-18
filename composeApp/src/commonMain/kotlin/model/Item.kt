package model

import ids.UUID

data class Item(var content: String, val subItems: MutableList<Item> = mutableListOf(), val id: String = UUID.generateUUID()) {

    fun add(subItem: Item) {
        subItems.add(subItem)
    }

    fun remove(subItem: Item) {
        subItems.remove(subItem)
    }
}