package model

import kotlinx.serialization.Serializable

@Serializable
data class SaveItem(val id: String, val subItems: List<SaveItem>) {
    companion object {
        fun from(item: Item): SaveItem {
            return SaveItem(item.id, item.subItems.map { from(it) })
        }
    }
}
