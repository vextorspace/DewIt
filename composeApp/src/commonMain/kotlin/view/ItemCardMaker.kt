package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.Item
import view.actions.OnAddCard
import view.actions.OnDeleteCard
import view.actions.OnEditCard

class ItemCardMaker(
    val item: Item,
    val selectedCard: MutableState<Item?>,
    val statusText: MutableState<String>,
    val onDelete: (Item) -> Unit
) {

    @Composable
    fun Compose(topLevel: Boolean = false) {
        val contentState = remember { mutableStateOf(item.content) }
        val subItems = remember { mutableStateOf(item.subItems) }

        Card(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    selectedCard.value = item
                }
                .background(chooseBackgroundColor()),
            elevation = 5.dp
        ) {
            Column {
                Row {
                    if(item == selectedCard.value) {
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {

                            TextField(
                                value = contentState.value,
                                onValueChange = {
                                    contentState.value = it
                                    item.content = it
                                }
                            )
                            Button(
                                onClick = {
                                selectedCard.value = null
                            }) {
                                Text("Done")
                            }
                        }
                    } else {
                        Text(contentState.value)
                    }
                    OnEditCard(selectedCard, item).Compose()
                    OnAddCard(statusText, subItems).Compose()
                    OnDeleteCard(item, onDelete).Compose()
                }

                if(topLevel) {
                    VerticalDisplay(selectedCard, statusText, onDelete).Compose(subItems)
                } else {
                        subItems.value.forEach { subItem ->
                            key(subItem.id) {
                                ItemCardMaker(
                                    subItem,
                                    selectedCard,
                                    statusText
                                ) { subItem -> deleteItemFromCard(subItems, subItem) }.Compose()
                            }
                        }
                }
            }
        }
    }

    private fun chooseBackgroundColor(): Color {
        return if (selectedCard.value == item) {
            Color.DarkGray
        } else {
            Color.White
        }
    }

    private fun deleteItemFromCard(
        parentItem: MutableState<MutableList<Item>>,
        it: Item
    ) {
        val newList = parentItem.value.toMutableList()
        newList.remove(it)
        parentItem.value = newList
    }
}