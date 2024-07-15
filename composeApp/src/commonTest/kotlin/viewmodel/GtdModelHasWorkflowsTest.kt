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

        // When
        val targetList = table(
            headers("source","target", "type"),
            row("Inbox","todo", ActionType.MOVE),
            row("Inbox","projects", ActionType.MOVE),
            row("Inbox", "someday maybe", ActionType.MOVE),
            row("Inbox", "references", ActionType.MOVE),

            row("Projects", "todo", ActionType.COPY),
            row("Projects", "someday maybe", ActionType.MOVE),
            row("Projects", "waiting on", ActionType.MOVE),

            row("Someday Maybe", "projects", ActionType.MOVE),
            row("Waiting On", "projects", ActionType.MOVE)
        )

        // Then
        forAll(targetList) { source, target, type ->
            val sourceItems = model.findContainingContent(source)
            val sourceItem = sourceItems.shouldNotBeNull()
                .shouldHaveSize(1)
                .first()
            val workflows = sourceItem.workflows

            workflows.filter { it.actionType == type }
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