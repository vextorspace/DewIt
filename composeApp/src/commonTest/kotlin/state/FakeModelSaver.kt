package state

import viewmodel.DewItViewModel

class FakeModelSaver: ModelSaver("fake", DewItViewModel()) {
    var saveCalled = false
    var loadCalled = false

    override fun save() {
        saveCalled = true
    }

    override fun load() {
        loadCalled = true
    }
}