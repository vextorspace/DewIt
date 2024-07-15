package viewmodel

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import io.kotest.matchers.collections.shouldHaveSize
import model.ActionType
import kotlin.test.Test

class GtdModelHasWorkflowsTest {

    @Test
    fun `GtdModel inbox has move to todo`() {
        // Given
        val model = GtdModel.createModel()

        // When
        val inbox = model.findContainingContent("Inbox")

        // Then
        inbox.shouldHaveSize(1)
        val inboxFlows = inbox.first().workflows

        inboxFlows.filter { it.actionType == ActionType.MOVE }
            .filter {
                model.findById(it.destination)
                    ?.content
                    ?.toLowerCase(Locale.current)
                    ?.contains("todo")
                    ?: false
            }
            .shouldHaveSize(1)

    }
}