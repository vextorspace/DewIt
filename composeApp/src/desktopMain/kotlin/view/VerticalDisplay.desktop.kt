package view

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import viewmodel.ViewItem

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class VerticalDisplay actual constructor(
    val selectedCard: MutableState<ViewItem?>,
    val statusText: MutableState<String>,
    val parentItems: MutableList<ViewItem>,
    val parentItemsState: MutableState<MutableList<ViewItem>>
){
    @Composable
    actual fun Compose() {
        val scrollState = rememberLazyListState()

        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                state = scrollState,
                horizontalAlignment = Alignment.Start
            ) {
                items(parentItemsState.value) { item ->
                    key(item.id) {
                        ItemCardMaker(
                            item,
                            parentItems,
                            parentItemsState,
                            selectedCard,
                            statusText
                        ).Compose(topLevel = false)
                    }
                }
            }

            VerticalScrollbar(adapter = rememberScrollbarAdapter(scrollState))
        }
    }
}