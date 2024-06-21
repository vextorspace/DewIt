package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

class OnAddCard(val statusText: MutableState<String>, val items: MutableState<MutableList<Item>>) {

    @Composable
    fun Compose() {
        IconButton(
            onClick = {
                val updateList = items.value.toMutableList()
                updateList.add(Item("New List"))
                items.value = updateList
                statusText.value = "Status: adding new list ${items.value.size}"
            }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "new"
            )
        }
    }
}