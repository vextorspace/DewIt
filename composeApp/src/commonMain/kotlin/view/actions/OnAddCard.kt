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

class OnAddCard(
    val statusText: MutableState<String>,
    val items: MutableList<Item>,
    val itemListState: MutableState<MutableList<Item>>
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
        items.add(newItem)
    }

    private fun addItemToStateList(newItem: Item) {
        val newList = itemListState.value.toMutableList()
        newList.add(newItem)
        itemListState.value = newList
    }
}