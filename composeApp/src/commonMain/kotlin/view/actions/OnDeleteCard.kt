package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import viewmodel.ViewItem

class OnDeleteCard(
    val viewItem: ViewItem,
    val parentItems: MutableList<ViewItem>,
    val parentItemsState: MutableState<MutableList<ViewItem>>,
    val statusText: MutableState<String>
) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = {
                removeItemFromStateListThenParent(viewItem)
                statusText.value = "Status: deleting ${viewItem.item.content}"
            }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }

    private fun removeItemFromStateListThenParent(item: ViewItem) {
        removeItemFromStateList(item, parentItemsState)
        parentItems.remove(item)
    }

    private fun removeItemFromStateList(
        item: ViewItem, parentListState: MutableState<MutableList<ViewItem>>
    ) {
        val newList = parentListState.value.toMutableList()
        newList.remove(item)
        parentListState.value = newList
    }
}
