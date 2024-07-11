package state

import resources.AppFile
import viewmodel.DewItViewModel

class AndroidModelSaver(private val fileName: String, private val model: DewItViewModel) : ModelSaver {

    override fun save() {
        val appFile = AppFile(fileName)
        appFile.create()
        appFile.writeText(model.toJson())
    }
}