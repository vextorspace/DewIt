package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.ItemList

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(val onDelete: (ItemList) -> Unit){
    @Composable
    actual fun Compose(itemLists: MutableState<MutableList<ItemList>>) {
    }
}