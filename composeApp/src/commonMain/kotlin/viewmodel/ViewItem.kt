package viewmodel

import kotlinx.serialization.Serializable
import model.Item

@Serializable
data class ViewItem(
    val item: Item = Item(),
    val parent: ViewItem? = null
) {
    val subItems: MutableList<ViewItem> = item.subItems.map { ViewItem(it, this) }.toMutableList()
}
