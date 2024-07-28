package viewmodel

import kotlinx.serialization.Serializable
import model.Item

@Serializable
data class ViewItem(
    val item: Item = Item(),
    var parent: ViewItem? = null
) {
    val subItems: MutableList<ViewItem> = item.subItems
        .map { ViewItem(it, this) }
        .toMutableList()
    var content:String by item::content
    val id by lazy { item.id }

    constructor(content: String) : this(Item(content))

    fun add(subItem: ViewItem) {
        if(subItems.contains(subItem))
            return
        subItem.parent = this
        subItems.add(subItem)
    }

    fun add(subItem: Item) {
        add(ViewItem(subItem, this))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ViewItem) return false

        if(parent != other.parent) return false
        if(item != other.item) return false
        if(subItems.hashCode() != other.subItems.hashCode()) return false
        return true
    }

    override fun hashCode(): Int {
        var result = item.hashCode()
        result = 31 * result + (parent?.hashCode() ?: 0)
        return result
    }

    fun allItems(): List<Item> {
        return subItems.flatMap { it.allItems() } + item
    }

    fun allViewItems(): List<ViewItem> {
        return subItems.flatMap { it.allViewItems() } + this
    }
}
