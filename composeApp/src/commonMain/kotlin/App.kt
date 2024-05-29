import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            makeCard("Create a card", "- create card body")
        }
    }
}

private fun makeCard(title: String, content: String) {
    Card(
        modifier = Modifier.padding(6.dp),
        elevation = 3.dp
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.h5
            )
            Text(content)
        }
    }
}