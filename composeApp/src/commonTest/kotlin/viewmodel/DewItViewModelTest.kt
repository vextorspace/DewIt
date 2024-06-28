package viewmodel

import androidx.compose.runtime.MutableState
import io.kotest.matchers.types.shouldBeInstanceOf
import model.Item
import kotlin.test.Test

class DewItViewModelTest {

    @Test
    fun `DewItViewModel provides a mutable state of mutable list`() {
        // Given
        val viewModel = DewItViewModel()

        // When
        val itemsState = viewModel.itemsState

        // Then
        itemsState.shouldBeInstanceOf<MutableState<MutableList<Item>>>()
    }
}