import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {

    Window(
        resizable = true,
        onCloseRequest = ::exitApplication,
        title = "DewIt"
    ) {
        App()
    }
}