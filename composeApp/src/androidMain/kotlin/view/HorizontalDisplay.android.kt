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
actual class HorizontalDisplay actual constructor(
    val statusText: MutableState<String>,
    val itemList: MutableList<Item>,
    val itemListState: MutableState<MutableList<Item>>
) {

    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    actual fun Compose() {
        val pagerState = rememberPagerState { itemListState.value.size }
        val selectedCard = remember { mutableStateOf<Item?>(null) }

        HorizontalPager(state = pagerState) { page ->
            ItemCardMaker(
                itemListState.value[page],
                itemList,
                itemListState,
                selectedCard,
                statusText
            ).Compose(topLevel = true)
        }
    }
}