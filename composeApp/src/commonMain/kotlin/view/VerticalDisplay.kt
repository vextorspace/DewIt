package view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import model.Item

class VerticalDisplay() {
    @Composable
    fun Compose(itemList: SnapshotStateList<Item>) {
        val selectedCard = remember { mutableStateOf<Item?>(null)}

        val onDeleteItem: (Item) -> Unit = {
            val index = itemList.indexOf(it)

            if(index != -1) {
                itemList.removeAt(index)
            }
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(itemList) { item ->
                key(item) {
                    ItemCardMaker(
                        item,
                        selectedCard,
                        onDeleteItem
                    ).compose()
                }
            }
        }
    }
}
