package view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import model.Item

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(val statusText: MutableState<String>) {

    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    actual fun Compose(itemList: MutableState<MutableList<Item>>) {
        val pagerState = rememberPagerState { itemList.value.size }
        val selectedCard = remember { mutableStateOf<Item?>(null) }

        HorizontalPager(state = pagerState) { page ->
            ItemCardMaker(
                itemList.value[page],
                selectedCard,
                statusText,
                itemList
            ).Compose(topLevel = true)
        }
    }
}