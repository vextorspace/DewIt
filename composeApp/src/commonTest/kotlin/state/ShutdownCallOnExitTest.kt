
package state

import io.kotest.matchers.shouldBe
import viewmodel.DewItViewModel
import kotlin.test.Test

class ShutdownCallOnExitTest {

    @Test
    fun `Shutdown Calls Save`() {
        val modelSaver = FakeModelSaver()
        val lifeCycle = LifeCycle(modelSaver)

        lifeCycle.onShutdown()

        modelSaver.saveCalled shouldBe true
    }
}

class FakeModelSaver: ModelSaver {
    var saveCalled = false

    override fun save() {
        saveCalled = true
    }

    override fun load(): DewItViewModel {
        throw NotImplementedError()
    }
}
