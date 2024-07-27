package view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(parentItemsState.value) { item ->
                key(item) {
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