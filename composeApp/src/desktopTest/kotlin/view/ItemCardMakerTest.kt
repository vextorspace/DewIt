package view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import model.Item
import kotlin.test.Test

class ItemCardMakerTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `actually displays content`() = runComposeUiTest {
        val itemContent = "::ITEMS CONTENT::"
        val item = Item(itemContent)
        val parentItems = mutableListOf<Item>()
        val parentItemsState = mutableStateOf(parentItems)
        val selectedCard = mutableStateOf<Item?>(null)
        val statusText = mutableStateOf("")

        setContent {
            ItemCardMaker(
                item,
                parentItems,
                parentItemsState,
                selectedCard,
                statusText
            ).Compose()
        }

        onNodeWithText(itemContent)
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `displays subitems`() = runComposeUiTest {
        val itemContent = "::ITEMS CONTENT::"
        val item = Item(itemContent)
        val subItemContent = "::SUBITEMS CONTENT::"
        val subItem = Item(subItemContent)
        item.add(subItem)
        val parentItems = mutableListOf<Item>()
        val parentItemsState = mutableStateOf(parentItems)
        val selectedCard = mutableStateOf<Item?>(null)
        val statusText = mutableStateOf("")

        setContent {
            ItemCardMaker(
                item,
                parentItems,
                parentItemsState,
                selectedCard,
                statusText
            ).Compose()
        }

        onNodeWithText(subItemContent)
            .assertIsDisplayed()
    }
}