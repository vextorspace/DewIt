package state

import viewmodel.DewItViewModel


interface ModelSaver {
    fun save()
    fun load(): DewItViewModel

    companion object{
        const val DEFAULT_SAVE_FILE_NAME = ".DewItNowSave.json"
    }
}
