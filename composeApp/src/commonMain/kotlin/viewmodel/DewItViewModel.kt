package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import model.Item

class DewItViewModel(initialItems: List<Item> = listOf()) {
    val itemsState: MutableState<MutableList<Item>> = mutableStateOf(initialItems.toMutableList())

    companion object {
        fun fromJson(viewModelJson: String): DewItViewModel {
            return DewItViewModel()
        }
    }
}
