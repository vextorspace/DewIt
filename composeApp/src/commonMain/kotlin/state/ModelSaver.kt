package state


interface ModelSaver {
    fun save()

    companion object{
        const val DEFAULT_SAVE_FILE_NAME = ".DewItNowSave.json"
    }
}
