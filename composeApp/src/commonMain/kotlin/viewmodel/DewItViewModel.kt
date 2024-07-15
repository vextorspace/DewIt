package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.json.Json
import model.Item

class DewItViewModel(val rootItem: Item = Item()) {
    constructor(initialItems: List<Item>)
            : this(Item("Root", initialItems.toMutableList()))

    val itemsState: MutableState<MutableList<Item>>
            = mutableStateOf(rootItem.subItems)

    fun toJson(): String {
        return encoder
            .encodeToString(Item.serializer(), rootItem)
    }

    fun addItem(newItem: Item) {
        rootItem.add(newItem)
    }

    fun findItemById(idToFind: String): Item? {
        return rootItem.findItemById(idToFind)
    }
    
    fun findContainingContent(content: String): List<Item> {
        return rootItem.findItemContaining(content)
    }

    fun findById(itemId: String): Item? {
        return rootItem.findItemById(itemId)
    }

    companion object {
        val encoder = Json { ignoreUnknownKeys = true }

        fun fromJson(viewModelJson: String): DewItViewModel {

            if(emptyJson(viewModelJson))
                return DewItViewModel()

            return loadMostRecentModel(encoder, viewModelJson)
        }

        private fun loadMostRecentModel(
            decoder: Json,
            viewModelJson: String
        ): DewItViewModel {
            try {
                val item: Item = decoder.decodeFromString(viewModelJson)
                return DewItViewModel(item)
            } catch (e: Exception) {
                return fromV0Json(viewModelJson)
            }
        }

        private fun fromV0Json(viewModelJson: String): DewItViewModel {
            val withoutSpaces = viewModelJson.replace("\\s".toRegex(), "")

            if(emptyJson(withoutSpaces))
                return DewItViewModel()

            val decoder = Json { ignoreUnknownKeys = true }
            val items: List<Item> = decoder.decodeFromString(viewModelJson)

            return DewItViewModel(items)
        }

        private fun emptyJson(viewModelJson: String): Boolean {
            val withoutSpaces = viewModelJson.replace("\\s".toRegex(), "")

            return withoutSpaces.isEmpty() || withoutSpaces == "[]"
        }
    }
}
