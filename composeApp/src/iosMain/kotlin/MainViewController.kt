import androidx.compose.ui.window.ComposeUIViewController
import state.LifeCycle
import state.ModelSaver
import viewmodel.GtdModel

fun MainViewController() = ComposeUIViewController {
    val lifeCycle = LifeCycle(
        ModelSaver(
            fileName = ModelSaver.DEFAULT_SAVE_FILE_NAME,
            defaultModel = GtdModel.createModel()
        )
    )
    App(lifeCycle)
}