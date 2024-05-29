import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import model.Item
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val cards = remember {mutableStateOf(listOf<Item>(Item("Card 1"), Item("Card 2"), Item("Card 3")))}
    val textState = remember { mutableStateOf("")}

    MaterialTheme {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                TextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    label = { Text("Enter text") }
                )
                Button(onClick = { cards.value += Item(textState.value) }) {
                    Text("Add Card")
                }
            }
            cards.value.map { it.makeCard() }
        }
    }
}
