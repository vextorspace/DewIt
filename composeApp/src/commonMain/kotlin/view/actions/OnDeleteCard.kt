package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

class OnDeleteCard(
    val item: Item,
    val parentItems: MutableList<Item>,
    val parentItemsState: MutableState<MutableList<Item>>
) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = {
                parentItems.remove(item)
                parentItemsState.value = parentItems
            }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
