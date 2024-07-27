package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import model.Item
import viewmodel.ViewItem

class OnAddCard(
    val statusText: MutableState<String>,
    val items: MutableList<ViewItem>,
    val itemListState: MutableState<MutableList<ViewItem>>
) {

    @Composable
    fun Compose() {
        val itemNumber = remember { mutableStateOf(0) }

        IconButton(
            onClick = {
                addItemToStateForDisplayAndItemForMemory(itemNumber)
                statusText.value = "Status: adding new list ${items.size}"
            }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "new"
            )
        }
    }

    private fun addItemToStateForDisplayAndItemForMemory(itemNumber: MutableState<Int>) {
        val newItem = Item("New Item ${itemNumber.value++}")
        addItemToStateList(newItem)
        addItemToParentItem(newItem)
    }

    private fun addItemToParentItem(newItem: Item) {
        items.add(ViewItem(newItem))
    }

    private fun addItemToStateList(newItem: Item) {
        val newList = itemListState.value.toMutableList()
        newList.add(ViewItem(newItem))
        itemListState.value = newList
    }
}