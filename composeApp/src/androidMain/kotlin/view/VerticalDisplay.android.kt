package view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class VerticalDisplay actual constructor(val selectedCard: MutableState<Item?>, val statusText: MutableState<String>, val onDelete: (Item) -> Unit) {
    @Composable
    actual fun Compose(itemList: MutableState<MutableList<Item>>) {
        val selectedCard = remember { mutableStateOf<Item?>(null)}

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(itemList.value) { item ->
                key(item.id) {
                    ItemCardMaker(
                        item,
                        selectedCard,
                        statusText,
                        onDelete
                    ).Compose()
                }
            }
        }
    }
}