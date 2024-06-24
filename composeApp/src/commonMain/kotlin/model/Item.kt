package model

import ids.UUID
import kotlinx.serialization.Serializable

@Serializable
data class Item(var content: String = "New Item", val subItems: MutableList<Item> = mutableListOf(), val id: String = UUID.generateUUID()) {
    constructor(content: String) : this(content, mutableListOf())
    constructor(content: String, subItems: Collection<Item>) : this(content, subItems.toMutableList())

    fun add(subItem: Item) {
        subItems.add(subItem)
    }

    fun remove(subItem: Item) {
        subItems.remove(subItem)
    }
}