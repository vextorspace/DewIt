package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import model.Item

class OnDeleteCard(val card: Item, val onDelete: (Item) -> Unit) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = { onDelete(card) }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
