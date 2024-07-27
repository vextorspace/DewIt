import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import state.LifeCycle
import view.HorizontalDisplay
import view.actions.OnAddCard

@Composable
@Preview
fun App(lifeCycle: LifeCycle) {

    val statusText = remember {
        mutableStateOf("Status: ${lifeCycle.model.itemsState.value.size}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DewIt Now") },
                actions = { OnAddCard(
                    statusText,
                    lifeCycle.model.itemsState.value,
                    lifeCycle.model.itemsState
                ).Compose() }
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
                itemList = lifeCycle.model.itemsState.value,
                itemListState = lifeCycle.model.itemsState
            ).Compose()
        }
    }
}

