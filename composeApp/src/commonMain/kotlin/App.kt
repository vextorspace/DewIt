import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import model.ItemList
import org.jetbrains.compose.ui.tooling.preview.Preview
import view.HorizontalDisplay
import view.actions.OnAddList

@Composable
@Preview
fun App() {
    val itemLists = remember {
        mutableStateOf(createFakeItemLists().toMutableList())
    }
    val statusText = remember {  mutableStateOf("Status: ${itemLists.value.size}")  }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DewIt Now")},
                actions = { OnAddList(statusText, itemLists).Compose() }
            )
        },
        bottomBar = {
            BottomAppBar() {
                Text(statusText.value, Modifier.padding(16.dp))
            }
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            HorizontalDisplay(onDelete = { deleteItemFromList(itemLists, it) })
                .Compose(itemLists)
        }
    }
}

private fun deleteItemFromList(
    itemLists: MutableState<MutableList<ItemList>>,
    it: ItemList
) {
    val newList = itemLists.value.toMutableList()
    newList.remove(it)
    itemLists.value 
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
                Item("Card 18"),
                Item("Card 19"),
                Item("Card 20"),
                Item("Card 21"),
                Item("Card 22"),
                Item("Card 23"),
                Item("Card 24"),
                Item("Card 25"),
                Item("Card 26"),
                Item("Card 27"),
                Item("Card 28"),
                Item("Card 29")
            ),
            "Inbox"
        ),
        ItemList(
            listOf(Item("Card 11", mutableListOf(Item("Sub 1"), Item("Sub 2", mutableListOf(Item("Sub Sub"))))), Item("Card 2"), Item("Card 3")),
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
