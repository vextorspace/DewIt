package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item
import model.ItemList

class OnAddList(val statusText: MutableState<String>, val itemLists: MutableState<MutableList<ItemList>>) {

    @Composable
    fun Compose() {
        IconButton(
            onClick = {
                val newList = ItemList(listOf(Item("New Item")), "New List")
                val updatedLists = itemLists.value.toMutableList()
                updatedLists.add(newList)
                itemLists.value = updatedLists
                statusText.value = "Status: adding new list ${itemLists.value.size}"
            }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "new"
            )
        }

    }
}