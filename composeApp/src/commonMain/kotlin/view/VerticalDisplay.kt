package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

expect class VerticalDisplay(
    selectedCard: MutableState<Item?>,
    statusText: MutableState<String>
) {
    @Composable
    fun Compose(itemList: MutableState<MutableList<Item>>)
}
