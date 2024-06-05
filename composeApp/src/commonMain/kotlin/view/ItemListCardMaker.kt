package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import model.ItemList

class ItemListCardMaker(val items: ItemList) {
    @Composable
    fun compose() {
        val itemList = remember {
            mutableStateListOf(*items.items.toTypedArray())
        }

        MaterialTheme {
            Card(Modifier.width(200.dp).fillMaxHeight()) {
                Column {
                    Text(
                        items.label,
                        style = MaterialTheme.typography.h4
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        VerticalDisplay().Compose(itemList)
                    }

                    Button(
                        onClick = { itemList += Item("New") }
                    ) {
                        Text(
                            "New Card",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}