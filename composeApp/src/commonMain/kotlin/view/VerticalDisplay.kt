package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class VerticalDisplay(
    selectedCard: MutableState<Item?>,
    statusText: MutableState<String>,
    parentItems: MutableList<Item>,
    parentItemsState: MutableState<MutableList<Item>>
) {
    @Composable
    fun Compose()
}
