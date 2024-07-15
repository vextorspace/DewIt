package viewmodel

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import model.ActionType
import kotlin.test.Test

class GtdModelHasWorkflowsTest {


    @Test
    fun `GtdModel inbox has move to todo and projects`() {
        // Given
        val model = GtdModel.createModel()
        val targetList = table(
            headers("source","target"),
            row("Inbox","todo"),
            row("Inbox","projects")
        )





        forAll(targetList) { source, target ->
            val sourceItems = model.findContainingContent(source)
            val sourceItem = sourceItems.shouldNotBeNull()
                .shouldHaveSize(1)
                .first()
            val workflows = sourceItem.workflows

            workflows.filter { it.actionType == ActionType.MOVE }
                .filter {
                    model.findById(it.destination)
                        ?.content
                        ?.toLowerCase(Locale.current)
                        ?.contains(target)
                        ?: false
                }
                .shouldHaveSize(1)
        }
    }
}