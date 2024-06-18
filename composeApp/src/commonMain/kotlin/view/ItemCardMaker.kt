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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.Item
import view.actions.OnDeleteCard

class ItemCardMaker(val item: Item, val selectedCard: MutableState<Item?>, val onDelete: (Item) -> Unit) {

    @Composable
    fun compose() {
        val contentState = remember { mutableStateOf(item.content) }
        val subItems = remember { mutableStateOf(item.subItems) }

        Card(
            modifier = Modifier
                .padding(5.dp)
                .clickable { selectedCard.value = item }
                .background(if(selectedCard.value == item) Color.DarkGray else Color.White),
            elevation = 5.dp
        ) {
            Column {
                Row {
                    if(item == selectedCard.value) {
                        TextField(
                            value = contentState.value,
                            onValueChange = {
                                contentState.value = it
                                item.content = it
                            }
                        )
                        Button(onClick = { onDelete(item) }) {
                            Text("Delete")
                        }
                    } else {
                        Text(contentState.value)
                    }
                    OnDeleteCard(item, onDelete).Compose()
                }
                if(subItems.value.isNotEmpty()) {
                    Column {
                        subItems.value.forEach {
                            ItemCardMaker(it, selectedCard) { itemToDelete: Item ->
                                deleteItemFromCard(
                                    subItems,
                                    itemToDelete
                                )
                            }.compose()
                        }
                    }
                }
            }
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