package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

class OnEditCard(val selectedCard: MutableState<Item?>, val item: Item) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = { selectedCard.value = item }
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
    }
}
