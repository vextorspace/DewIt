package state

import resources.AppFile
import viewmodel.DewItViewModel
import viewmodel.GtdModel

class AndroidModelSaver(private val fileName: String, private val model: DewItViewModel) : ModelSaver {

    override fun save() {
        val appFile = AppFile(fileName)
        appFile.writeText(model.toJson())
    }

    override fun load(): DewItViewModel {
        val appFile = AppFile(fileName)
        val json = appFile.readText()
        return json?.let { DewItViewModel.fromJson(it) } ?: GtdModel.createModel()
    }
}