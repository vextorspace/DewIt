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
    val parentItemsState: MutableState<MutableList<Item>>,
    val statusText: MutableState<String>
) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = {
                removeItemFromStateListThenParent(item)
                statusText.value = "Status: deleting ${item.content}"
            }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }

    private fun removeItemFromStateListThenParent(item: Item) {
        removeItemFromStateList(item, parentItemsState)
        parentItems.remove(item)
    }

    private fun removeItemFromStateList(
        item: Item, parentListState: MutableState<MutableList<Item>>
    ) {
        val newList = parentListState.value.toMutableList()
        newList.remove(item)
        parentListState.value = newList
    }
}
