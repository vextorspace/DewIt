package view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import viewmodel.ViewItem

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class VerticalDisplay actual constructor(
    val selectedCard: MutableState<ViewItem?>,
    val statusText: MutableState<String>,
    val parentItems: MutableList<ViewItem>,
    val parentItemsState: MutableState<MutableList<ViewItem>>
) {
    @Composable
    actual fun Compose() {
        LazyColumn(
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
                    ).Compose()
                }
            }
        }
    }
}