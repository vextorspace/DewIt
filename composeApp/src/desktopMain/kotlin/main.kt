import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import state.LifeCycle
import state.ModelSaver
import viewmodel.GtdModel

fun main() = application {
    val model = GtdModel.createModel()
    val lifeCycle = LifeCycle(ModelSaver(ModelSaver.DEFAULT_SAVE_FILE_NAME, model))

    lifeCycle.onInit()

    Window(
        resizable = true,
        onCloseRequest = {
            lifeCycle.onShutdown()
            ::exitApplication
        },
        title = "DewIt"
    ) {
        App(lifeCycle)
    }
}