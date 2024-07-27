package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import viewmodel.ViewItem

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class VerticalDisplay(
    selectedCard: MutableState<ViewItem?>,
    statusText: MutableState<String>,
    parentItems: MutableList<ViewItem>,
    parentItemsState: MutableState<MutableList<ViewItem>>
) {
    @Composable
    fun Compose()
}
