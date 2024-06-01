import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Item
import org.jetbrains.compose.ui.tooling.preview.Preview
import view.ItemCardMaker


@Composable
@Preview
fun App() {
    val itemList = remember {mutableStateListOf(Item("Card 1"), Item("Card 2"), Item("Card 3"))}
    val textState = remember { mutableStateOf("")}
    val selectedCard = remember { mutableStateOf<Item?>(null)}

    MaterialTheme {
        LazyColumn(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row {
                    TextField(
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        label = { Text("Enter text") }
                    )
                    Button(onClick = { itemList += Item(textState.value) }) {
                        Text("New Card")
                    }
                }
            }
            items(itemList){ item -> ItemCardMaker(item, selectedCard).compose() }
            item {
                Text("Selected card: ${selectedCard.value?.content ?: "None"}")
            }
        }
    }
}
