package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.json.Json
import model.Item
import model.visitors.FindItemByIdAction
import model.visitors.ItemVisitor

class DewItViewModel(val rootItem: Item = Item()) {
    constructor(initialItems: List<Item>) : this(Item("Root", initialItems.toMutableList()))

    val itemsState: MutableState<MutableList<Item>> = mutableStateOf(rootItem.subItems)

    fun toJson(): String {
        return encoder
            .encodeToString(Item.serializer(), rootItem)
    }

    fun addItem(newItem: Item) {
        rootItem.add(newItem)
    }

    fun findItemById(idToFind: String): Item? {
        val findItemByIdAction = FindItemByIdAction(idToFind)
        ItemVisitor(findItemByIdAction).visit(rootItem)
        return findItemByIdAction.item
    }

    companion object {
        val encoder = Json { ignoreUnknownKeys = true }

        fun fromJson(viewModelJson: String): DewItViewModel {
            val withoutSpaces = viewModelJson.replace("\\s".toRegex(), "")

            if(withoutSpaces.isEmpty() || withoutSpaces == "[]")
                return DewItViewModel()

            val decoder = Json { ignoreUnknownKeys = true }
            try {
                val item: Item = decoder.decodeFromString(viewModelJson)
                return DewItViewModel(item)
            } catch (e: Exception) {
                return fromV0Json(viewModelJson)
            }
        }

        fun fromV0Json(viewModelJson: String): DewItViewModel {
            val withoutSpaces = viewModelJson.replace("\\s".toRegex(), "")

            if(withoutSpaces.isEmpty() || withoutSpaces == "[]")
                return DewItViewModel()

            val decoder = Json { ignoreUnknownKeys = true }
            val items: List<Item> = decoder.decodeFromString(viewModelJson)
            return DewItViewModel(items)
        }
    }
}
