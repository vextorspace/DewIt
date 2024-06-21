import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import org.jetbrains.compose.ui.tooling.preview.Preview
import view.HorizontalDisplay
import view.actions.OnAddCard

@Composable
@Preview
fun App() {
    val itemList = remember {
        mutableStateOf(createGtdList().toMutableList())
    }
    val statusText = remember {
        mutableStateOf("Status: ${itemList.value.size}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DewIt Now")},
                actions = { OnAddCard(statusText, itemList).Compose() }
            )
        },
        bottomBar = {
            BottomAppBar() {
                Text(statusText.value, Modifier.padding(16.dp))
            }
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            HorizontalDisplay(
                statusText = statusText
            ).Compose(itemList)
        }
    }
}

private fun createGtdList(): List<Item> {
    val itemList = listOf(

                Item(
                "Inbox",
                listOf(
                    Item("Review How You Use This App")
                )
        ),
        Item(
            "Todo",
            listOf(
                Item("Read GTD Book"),
                Item("Do a Dump")
            )
        ),
        Item(
            "Projects"
        ),
        Item(
            "Someday Maybe"
        ),
        Item(
            "Waiting On"
        ),
        Item(
            "References"
        )
    )

    return itemList
}
