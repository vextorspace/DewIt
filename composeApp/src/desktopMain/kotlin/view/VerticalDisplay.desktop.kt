package view

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Item

actual class VerticalDisplay actual constructor() {
    @Composable
    actual fun Compose(itemList: SnapshotStateList<Item>) {
        val selectedCard = remember { mutableStateOf<Item?>(null)}
        val scrollState = rememberLazyListState()

        val onDeleteItem: (Item) -> Unit = {
            val index = itemList.indexOf(it)

            if(index != -1) {
                itemList.removeAt(index)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                state = scrollState,
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

            VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
        }
    }
}