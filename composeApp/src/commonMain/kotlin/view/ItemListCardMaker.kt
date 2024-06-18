package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import model.ItemList
import view.actions.OnDeleteList

class ItemListCardMaker(val items: ItemList, val onDelete: (ItemList) -> Unit) {
    @Composable
    fun compose() {
        val itemList = remember {
            mutableStateListOf(*items.items.toTypedArray())
        }

        MaterialTheme {
            Card(Modifier.width(200.dp).fillMaxHeight()) {
                Column {
                    MakeTitleHeader()

                    Column(modifier = Modifier.weight(1f)) {
                        VerticalDisplay().Compose(itemList)
                    }

                    MakeNewButtonFooter(itemList)
                }
            }
        }
    }

    @Composable
    private fun MakeNewButtonFooter(itemList: SnapshotStateList<Item>) {
        Button(
            onClick = { itemList += Item("New") }
        ) {
            Text(
                "New Card",
                style = MaterialTheme.typography.body2
            )
        }
    }

    @Composable
    private fun MakeTitleHeader() {
        Row {
            Text(
                items.label,
                style = MaterialTheme.typography.h4
            )
            OnDeleteList(items, onDelete).Compose()
        }
    }
}