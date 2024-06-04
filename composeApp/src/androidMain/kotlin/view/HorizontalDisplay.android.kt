package view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import model.ItemList

actual class HorizontalDisplay actual constructor() {

    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    actual fun Compose(itemLists: List<ItemList>) {
        val pagerState = rememberPagerState { itemLists.size }

        HorizontalPager(state = pagerState) { page ->
            ItemListCardMaker(itemLists[page]).compose()
        }
    }
}