import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import model.Item
import model.ItemList
import view.ItemListCardMaker
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    val itemLists = listOf(
        ItemList(listOf(Item("Card 01"), Item("Card 2"), Item("Card 3")), "Inbox"),
        ItemList(listOf(Item("Card 11"), Item("Card 2"), Item("Card 3")), "Inbox"),
        ItemList(listOf(Item("Card 21"), Item("Card 2"), Item("Card 3")), "Inbox"),
        ItemList(listOf(Item("Card 31"), Item("Card 2"), Item("Card 3")), "Inbox"),
        ItemList(listOf(Item("Card 41"), Item("Card 2"), Item("Card 3")), "Inbox"),
        ItemList(listOf(Item("Card 51"), Item("Card 2"), Item("Card 3")), "Inbox"),
        ItemList(listOf(Item("Card 61"), Item("Card 2"), Item("Card 3")), "Inbox"),
    )
    val pagerState = rememberPagerState{5}

    HorizontalPager(state=pagerState) { page ->
        itemLists.forEach { ItemListCardMaker(it).compose() }
    }
}
