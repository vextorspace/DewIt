package view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import model.Item

expect class VerticalDisplay() {
    @Composable
    fun Compose(itemList: SnapshotStateList<Item>)
}
