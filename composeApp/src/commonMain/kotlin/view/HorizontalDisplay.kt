package view

import androidx.compose.runtime.Composable

expect class HorizontalDisplay() {
    @Composable
    fun Compose(itemLists: List<model.ItemList>)
}