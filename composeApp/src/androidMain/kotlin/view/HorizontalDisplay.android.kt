package view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import model.ItemList

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class HorizontalDisplay actual constructor(val onDelete: (ItemList) -> Unit) {

    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    actual fun Compose(itemLists: MutableState<MutableList<ItemList>>) {
        val pagerState = rememberPagerState { itemLists.value.size }

        HorizontalPager(state = pagerState) { page ->
            ItemListCardMaker(itemLists.value[page], onDelete).compose()
        }
    }
}