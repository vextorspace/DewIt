package state

import resources.AppFile
import viewmodel.DewItViewModel

class DesktopModelSaver(private val fileName: String, private val model: DewItViewModel) : ModelSaver {
    override fun save() {
        val appFile = AppFile(fileName)
        appFile.writeText(model.toJson())
    }
}
