package view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class VerticalDisplay actual constructor(
    val selectedCard: MutableState<Item?>,
    val statusText: MutableState<String>,
    val parentItems: MutableList<Item>,
    val parentItemsState: MutableState<MutableList<Item>>
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