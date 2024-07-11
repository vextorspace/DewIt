package state

import io.kotest.matchers.shouldBe
import viewmodel.DewItViewModel
import kotlin.test.Test

class InitializationCallsLoadTest {

    @Test
    fun `Shutdown Calls Save`() {
        val modelSaver = FakeModelSaver()
        val lifeCycle = LifeCycle(modelSaver)

        lifeCycle.onInit()

        modelSaver.loadCalled shouldBe true
    }
}