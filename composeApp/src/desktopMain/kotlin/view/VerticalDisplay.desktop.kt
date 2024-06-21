package view

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class VerticalDisplay actual constructor(
    val selectedCard: MutableState<Item?>,
    val statusText: MutableState<String>
){
    @Composable
    actual fun Compose(itemList: MutableState<MutableList<Item>>) {
        val scrollState = rememberLazyListState()

        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                state = scrollState,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(itemList.value) { item ->
                    key(item.id) {
                        ItemCardMaker(
                            item,
                            selectedCard,
                            statusText,
                            itemList
                        ).Compose(topLevel = false)
                    }
                }
            }

            VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
        }
    }
}