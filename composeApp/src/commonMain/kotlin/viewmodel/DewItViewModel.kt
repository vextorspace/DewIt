package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.json.Json
import model.Item

class DewItViewModel(initialItems: List<Item> = listOf()) {
    val itemsState: MutableState<MutableList<Item>> = mutableStateOf(initialItems.toMutableList())

    companion object {
        fun fromJson(viewModelJson: String): DewItViewModel {
            val withoutSpaces = viewModelJson.replace("\\s".toRegex(), "")

            if(withoutSpaces.isEmpty() || withoutSpaces == "[]")
                return DewItViewModel()

            val decoder = Json { ignoreUnknownKeys = true }
            val itemList: List<Item> = decoder.decodeFromString(viewModelJson)
            return DewItViewModel(itemList)
        }
    }
}
