package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import viewmodel.ViewItem

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HorizontalDisplay(
    statusText: MutableState<String>,
    itemList: MutableList<ViewItem>,
    itemListState: MutableState<MutableList<ViewItem>>
) {
    @Composable
    fun Compose()
}