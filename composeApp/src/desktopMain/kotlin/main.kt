import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import state.DesktopModelSaver
import state.LifeCycle
import state.ModelSaver
import viewmodel.GtdModel

fun main() = application {
    val model = GtdModel.createModel()
    val lifeCycle = LifeCycle(DesktopModelSaver(ModelSaver.DEFAULT_SAVE_FILE_NAME, model))

    Window(
        resizable = true,
        onCloseRequest = {
            lifeCycle.onShutdown()
            ::exitApplication
        },
        title = "DewIt"
    ) {
        App()
    }
}