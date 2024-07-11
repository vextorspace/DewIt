package state

class LifeCycle(val modelSaver: ModelSaver) {
    fun onShutdown() {
        modelSaver.save()
    }

}
