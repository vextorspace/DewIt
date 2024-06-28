package viewmodel

import androidx.compose.runtime.MutableState
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
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

    @Test
    fun `When DewItViewModel is created the mutable list is empty`() {
        // Given
        val viewModel = DewItViewModel()

        // When
        val items = viewModel.itemsState.value

        // Then
        items.shouldBeEmpty()
    }

    @Test
    fun `DewItViewModel reconstituted from an empty json string gives empty list`() {
        // Given
        val viewModelJson = "[]"

        // When
        val viewModel = DewItViewModel.fromJson(viewModelJson)

        // Then
        viewModel.itemsState.value.shouldBeEmpty()
    }
}