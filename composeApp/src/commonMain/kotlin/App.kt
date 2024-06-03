import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import model.ItemList
import org.jetbrains.compose.ui.tooling.preview.Preview
import view.ItemCardMaker


@Composable
@Preview
fun App() {
    val itemList = ItemList(listOf(Item("Card 1"), Item("Card 2"), Item("Card 3")), "Inbox")
    itemList.compose()
}
