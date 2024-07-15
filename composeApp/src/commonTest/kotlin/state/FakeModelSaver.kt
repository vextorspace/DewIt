package state

import model.Item
import viewmodel.DewItViewModel

class FakeModelSaver: ModelSaver("fake", DewItViewModel(rootItem = Item())) {
    var saveCalled = false
    var loadCalled = false

    override fun save() {
        saveCalled = true
    }

    override fun load() {
        loadCalled = true
    }
}