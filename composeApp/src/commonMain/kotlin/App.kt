import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import model.Item
import model.ItemList
import org.jetbrains.compose.ui.tooling.preview.Preview
import view.HorizontalDisplay

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    val itemLists = createFakeItemLists()

    HorizontalDisplay().Compose(itemLists)
}


private fun createFakeItemLists(): List<ItemList> {
    val itemLists = listOf(
        ItemList(
            listOf(
                Item("Card 01"),
                Item("Card 2"),
                Item("Card 3"),
                Item("Card 4"),
                Item("Card 5"),
                Item("Card 6"),
                Item("Card 7"),
                Item("Card 8"),
                Item("Card 9"),
                Item("Card 10"),
                Item("Card 11"),
                Item("Card 12"),
                Item("Card 13"),
                Item("Card 14"),
                Item("Card 15"),
                Item("Card 16"),
                Item("Card 17"),
                Item("Card 18")
            ),
            "Inbox"
        ),
        ItemList(
            listOf(Item("Card 11"), Item("Card 2"), Item("Card 3")),
            "Inbox"
        ),
        ItemList(
            listOf(Item("Card 21"), Item("Card 2"), Item("Card 3")),
            "Inbox"
        ),
        ItemList(
            listOf(Item("Card 31"), Item("Card 2"), Item("Card 3")),
            "Inbox"
        ),
        ItemList(
            listOf(Item("Card 41"), Item("Card 2"), Item("Card 3")),
            "Inbox"
        ),
        ItemList(
            listOf(Item("Card 51"), Item("Card 2"), Item("Card 3")),
            "Inbox"
        ),
        ItemList(
            listOf(Item("Card 61"), Item("Card 2"), Item("Card 3")),
            "Inbox"
        ),
    )
    return itemLists
}
