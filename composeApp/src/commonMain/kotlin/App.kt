import androidx.compose.runtime.*
import model.Item
import model.ItemList
import view.ItemListCardMaker
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val itemLists = listOf(ItemList(listOf(Item("Card 1"), Item("Card 2"), Item("Card 3")), "Inbox"))

    HorizontalPager() {
        itemLists.forEach { ItemListCardMaker(it).compose() }
    }
}
