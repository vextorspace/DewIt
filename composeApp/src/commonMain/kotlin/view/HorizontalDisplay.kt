package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class HorizontalDisplay(
    statusText: MutableState<String>,
    itemList: MutableList<Item>,
    itemListState: MutableState<MutableList<Item>>
) {
    @Composable
    fun Compose()
}