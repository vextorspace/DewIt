package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(
    val statusText: MutableState<String>,
    val itemList: MutableList<Item>,
    val itemListState: MutableState<MutableList<Item>>
){
    @Composable
    actual fun Compose() {
    }
}