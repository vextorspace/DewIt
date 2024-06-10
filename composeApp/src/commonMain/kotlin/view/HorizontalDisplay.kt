package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.ItemList

expect class HorizontalDisplay() {
    @Composable
    fun Compose(itemLists: MutableState<MutableList<ItemList>>)
}