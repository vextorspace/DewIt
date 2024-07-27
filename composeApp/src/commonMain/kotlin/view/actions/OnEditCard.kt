package view.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import viewmodel.ViewItem

class OnEditCard(val selectedCard: MutableState<ViewItem?>, val viewItem: ViewItem) {
    @Composable
    fun Compose() {
        IconButton(
            onClick = { selectedCard.value = viewItem }
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
    }
}
