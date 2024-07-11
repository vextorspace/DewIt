package state

class LifeCycle(val modelSaver: ModelSaver) {
    val model get() = modelSaver.model

    fun onShutdown() {
        modelSaver.save()
    }

    fun onInit() {
        modelSaver.load()
    }

}
