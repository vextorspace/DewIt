package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(val statusText: MutableState<String>){
    @Composable
    actual fun Compose(itemList: MutableState<MutableList<Item>>) {
    }
}