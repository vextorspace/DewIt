package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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

class ItemCardMaker(val item: Item, val selectedCard: MutableState<Item?>) {

    @Composable
    fun compose() {
        val contentState = remember { mutableStateOf(item.content) }

        Card(
            modifier = Modifier
                .padding(5.dp)
                .clickable { selectedCard.value = item }
                .background(if(selectedCard.value == item) Color.DarkGray else Color.White),
            elevation = 5.dp
        ) {
            Column {
                if(item == selectedCard.value) {
                    TextField(
                        value = item.content,
                        onValueChange = {
                            contentState.value = it
                            item.content = it
                        }
                    )
                } else {
                    Text(item.content)
                }
            }
        }
    }
}