import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import org.jetbrains.compose.ui.tooling.preview.Preview
import resources.AppFile
import view.HorizontalDisplay
import view.actions.OnAddCard

@Composable
@Preview
fun App() {
    val itemListState = rememberSaveable {
        mutableStateOf(createGtdList())
    }
    val statusText = remember {
        mutableStateOf("Status: ${itemListState.value.size}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DewIt Now") },
                actions = { OnAddCard(statusText, itemListState.value, itemListState).Compose() }
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
                statusText = statusText,
                itemList = itemListState.value,
                itemListState = itemListState
            ).Compose()
        }


        
    }
}

private fun createGtdList(): MutableList<Item> {
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

    return itemList.toMutableList()
}
