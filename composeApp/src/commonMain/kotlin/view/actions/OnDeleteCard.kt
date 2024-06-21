package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

class OnDeleteCard(val card: Item, val parentItems: MutableState<MutableList<Item>>) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = {
                val newList = parentItems.value.toMutableList()
                newList.remove(card)
                parentItems.value = newList
            }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
