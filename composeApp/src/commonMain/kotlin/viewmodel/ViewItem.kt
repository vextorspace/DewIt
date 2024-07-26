package viewmodel

import kotlinx.serialization.Serializable
import model.Item

@Serializable
data class ViewItem(val item: Item = Item()) {
    val subItems: MutableList<ViewItem> = item.subItems.map { ViewItem(it) }.toMutableList()
}
