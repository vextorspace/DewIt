package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import viewmodel.ViewItem

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(
    val statusText: MutableState<String>,
    val itemList: MutableList<ViewItem>,
    val itemListState: MutableState<MutableList<ViewItem>>
){
    @Composable
    actual fun Compose() {
    }
}