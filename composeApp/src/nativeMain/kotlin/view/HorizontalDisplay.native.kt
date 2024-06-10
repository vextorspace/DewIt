package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.ItemList

actual class HorizontalDisplay actual constructor() {
    @Composable
    actual fun Compose(itemLists: MutableState<MutableList<ItemList>>) {
    }
}