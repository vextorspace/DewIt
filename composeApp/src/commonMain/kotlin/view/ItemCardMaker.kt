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
import view.actions.OnAddCard
import view.actions.OnDeleteCard
import view.actions.OnEditCard
import viewmodel.ViewItem

class ItemCardMaker(
    val viewItem: ViewItem,
    val parentItems: MutableList<ViewItem>,
    val parentItemsState: MutableState<MutableList<ViewItem>>,
    val selectedCard: MutableState<ViewItem?>,
    val statusText: MutableState<String>
) {

    @Composable
    fun Compose(topLevel: Boolean = false) {
        val contentState = remember { mutableStateOf(viewItem.item.content) }
        val subItems = remember { mutableStateOf(viewItem.subItems) }

        Card(
            modifier = Modifier
                .padding(5.dp)
                .clickable {
                    selectedCard.value = viewItem
                }
                .background(chooseBackgroundColor()),
            elevation = 5.dp
        ) {
            Column {
                Row {
                    if(viewItem == selectedCard.value) {
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {

                            TextField(
                                value = contentState.value,
                                onValueChange = {
                                    contentState.value = it
                                    viewItem.item.content = it
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
                    OnEditCard(selectedCard, viewItem).Compose()
                    OnAddCard(statusText, viewItem.subItems, subItems).Compose()
                    OnDeleteCard(viewItem, parentItems, parentItemsState, statusText).Compose()
                }

                if(topLevel) {
                    VerticalDisplay(
                        selectedCard,
                        statusText,
                        viewItem.subItems,
                        subItems
                    ).Compose()
                } else {
                    subItems.value.forEach { subItem ->
                        key(subItem.id) {
                            ItemCardMaker(
                                subItem,
                                viewItem.subItems,
                                subItems,
                                selectedCard,
                                statusText
                            ).Compose()
                        }
                    }
                }
            }
        }
    }

    private fun chooseBackgroundColor(): Color {
        return if (selectedCard.value == viewItem) {
            Color.DarkGray
        } else {
            Color.White
        }
    }
}