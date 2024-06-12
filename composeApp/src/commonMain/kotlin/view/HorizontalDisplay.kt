package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.ItemList

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HorizontalDisplay() {
    @Composable
    fun Compose(itemLists: MutableState<MutableList<ItemList>>)
}