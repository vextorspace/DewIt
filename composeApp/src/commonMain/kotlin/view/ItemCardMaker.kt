package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item

class ItemCardMaker(val item: Item) {

    @Composable
    fun compose() {
        Card(
            modifier = Modifier
                .padding(6.dp),
            elevation = 3.dp
        ) {
            Column {
                Text(item.content)
            }
        }
    }

}