package model

import kotlinx.serialization.Serializable
import viewmodel.ViewItem

@Serializable
data class SaveItem(val id: String, val subItems: List<SaveItem>) {
    companion object {
        fun from(item: ViewItem): SaveItem {
            return SaveItem(item.id, item.subItems.map { from(it) })
        }
    }
}
