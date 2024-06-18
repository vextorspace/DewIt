package view

import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import model.ItemList

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(val onDelete: (ItemList) -> Unit) {
    @Composable
    actual fun Compose(itemLists: MutableState<MutableList<ItemList>>) {
        val scrollState = rememberLazyListState()

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            LazyRow(state = scrollState, modifier = Modifier.weight(1f)) {
                items(itemLists.value) { itemList ->
                    key(itemList.id) {
                        ItemListCardMaker(itemList, onDelete).compose()
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