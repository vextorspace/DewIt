package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

expect class VerticalDisplay(selectedCard: MutableState<Item?>, statusText: MutableState<String>, onDelete: (Item) -> Unit) {
    @Composable
    fun Compose(itemList: MutableState<MutableList<Item>>)
}
