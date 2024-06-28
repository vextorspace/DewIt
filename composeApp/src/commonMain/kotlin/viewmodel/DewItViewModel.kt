package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import model.Item

class DewItViewModel {
    val itemsState: MutableState<MutableList<Item>> = mutableStateOf(mutableListOf())
}
