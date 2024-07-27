package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.json.Json
import model.Item

class DewItViewModel(val item: ViewItem = ViewItem()) {
    constructor(item: Item) : this(ViewItem(item))
    constructor(initialItems: List<Item>) : this(Item("Root", initialItems.toMutableList()))

    val itemsState: MutableState<MutableList<ViewItem>> = mutableStateOf(item.subItems)

    fun toJson(): String {
        return encoder
            .encodeToString(Item.serializer(), item.item)
    }

    fun addItem(newItem: ViewItem) {
        addItem(newItem.item)
    }

    fun addItem(newItem: Item) {
        if(item.subItems.map { it.id }.contains(newItem.id))
            return
        item.add(ViewItem(newItem))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DewItViewModel) return false

        if(item != other.item) return false
        return true
    }

    override fun hashCode(): Int {
        return item.hashCode()
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
