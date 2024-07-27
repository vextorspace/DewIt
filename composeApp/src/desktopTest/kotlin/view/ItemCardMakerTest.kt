package view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import model.Item
import viewmodel.ViewItem
import kotlin.test.Test

class ItemCardMakerTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `actually displays content`() = runComposeUiTest {
        val itemContent = "::ITEMS CONTENT::"
        val item = Item(itemContent)
        val viewItem = ViewItem(item)

        val parentItems = mutableListOf<ViewItem>()
        val parentItemsState = mutableStateOf(parentItems)
        val selectedCard = mutableStateOf<ViewItem?>(null)
        val statusText = mutableStateOf("")

        setContent {
            ItemCardMaker(
                viewItem,
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
        val viewItem = ViewItem(item)
        val subItemContent = "::SUBITEMS CONTENT::"
        val subItem = Item(subItemContent)
        viewItem.add(subItem)
        val parentItems = mutableListOf<ViewItem>()
        val parentItemsState = mutableStateOf(parentItems)
        val selectedCard = mutableStateOf<ViewItem?>(null)
        val statusText = mutableStateOf("")

        setContent {
            ItemCardMaker(
                viewItem,
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