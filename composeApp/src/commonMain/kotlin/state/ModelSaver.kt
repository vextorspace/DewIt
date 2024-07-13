package state

import resources.AppFile
import viewmodel.DewItViewModel
import viewmodel.GtdModel


open class ModelSaver(private val fileName: String, defaultModel: DewItViewModel) {
    var model = defaultModel

    open fun save() {
        val appFile = AppFile(fileName)
        appFile.writeText(model.toJson())
    }

    open fun load() {
        val appFile = AppFile(fileName)
        val json = appFile.readText()
        model = json?.let {
            DewItViewModel.fromJson(it)
        } ?: GtdModel.createModel()
    }

    companion object{
        const val DEFAULT_SAVE_FILE_NAME = ".DewItNowSave.json"
    }
}
