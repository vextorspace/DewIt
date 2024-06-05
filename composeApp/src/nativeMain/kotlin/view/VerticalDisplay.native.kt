package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import model.Item

actual class VerticalDisplay actual constructor() {
    @Composable
    actual fun Compose(itemList: SnapshotStateList<Item>) {
    }
}