package view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import model.Item

actual class VerticalDisplay actual constructor(val selectedCard: MutableState<Item?>, val statusText: MutableState<String>, val onDelete: (Item) -> Unit) {
    @Composable
    actual fun Compose(itemList: MutableState<MutableList<Item>>) {
        val selectedCard = remember { mutableStateOf<Item?>(null)}

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(itemList.value) { item ->
                key(item) {
                    ItemCardMaker(
                        item,
                        selectedCard,
                        statusText,
                        onDelete
                    ).compose()
                }
            }
        }
    }
}