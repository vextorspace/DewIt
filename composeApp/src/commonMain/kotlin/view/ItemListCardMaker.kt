package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import model.ItemList

class ItemListCardMaker(val items: ItemList) {
    @Composable
    fun compose() {
        val itemList = remember {mutableStateListOf<Item>(*items.items.toTypedArray())}
        val selectedCard = remember { mutableStateOf<Item?>(null)}

        val onDeleteItem: (Item) -> Unit = {
            val index = itemList.indexOf(it)

            if(index != -1) {
                itemList.removeAt(index)
            }
        }

        MaterialTheme {
            Card(Modifier.width(200.dp)) {
                Column {
                    Text(items.label, style = MaterialTheme.typography.h4)

                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(itemList) { item ->
                            key(item) {
                                ItemCardMaker(
                                    item,
                                    selectedCard,
                                    onDeleteItem
                                ).compose()
                            }
                        }
                    }

                    Button(onClick = { itemList += Item("New") }) {
                        Text("New Card", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}