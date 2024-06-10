package view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.ItemList

actual class HorizontalDisplay actual constructor() {

    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    actual fun Compose(itemLists: MutableState<MutableList<ItemList>>) {
        val pagerState = rememberPagerState { itemLists.value.size }

        HorizontalPager(state = pagerState) { page ->
            ItemListCardMaker(itemLists.value[page]).compose()
        }
    }
}