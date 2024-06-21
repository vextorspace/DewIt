package view

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class VerticalDisplay actual constructor(val selectedCard: MutableState<Item?>, val statusText: MutableState<String>, val onDelete: (Item) -> Unit){
    @Composable
    actual fun Compose(itemList: MutableState<MutableList<Item>>) {
        val scrollState = rememberLazyListState()

        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                state = scrollState,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                items(itemList.value) { item ->
                    key(item.id) {
                        ItemCardMaker(
                            item,
                            selectedCard,
                            statusText,
                            onDelete
                        ).compose()
                    }
                }
            }

            VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
        }
    }
}