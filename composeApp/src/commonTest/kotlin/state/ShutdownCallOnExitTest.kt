
package state

import io.kotest.matchers.shouldBe
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

