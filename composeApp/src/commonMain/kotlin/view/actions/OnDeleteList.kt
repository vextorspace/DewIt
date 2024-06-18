package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import model.ItemList

class OnDeleteList(val itemList: ItemList, val onDelete: (ItemList) -> Unit) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = { onDelete(itemList) }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
