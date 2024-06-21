package view

import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(val statusText: MutableState<String>, val onDelete: (Item) -> Unit) {
    @Composable
    actual fun Compose(itemList: MutableState<MutableList<Item>>) {
        val scrollState = rememberLazyListState()
        val selectedItem = remember { mutableStateOf<Item?>(null) }

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            LazyRow(state = scrollState, modifier = Modifier.weight(1f)) {
                items(itemList.value) { item ->
                    key(item.id) {
                        ItemCardMaker(item, selectedItem, statusText, onDelete).Compose(topLevel = true)
                    }
                }
            }

            HorizontalScrollbar(
                adapter = rememberScrollbarAdapter(scrollState),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}