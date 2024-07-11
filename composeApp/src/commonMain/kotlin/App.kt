import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import view.HorizontalDisplay
import view.actions.OnAddCard
import viewmodel.GtdModel

@Composable
@Preview
fun App() {
    val itemListState = rememberSaveable {
        mutableStateOf(GtdModel.createList())
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

