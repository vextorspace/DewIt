package model

import io.kotest.matchers.collections.shouldBeEmpty
import kotlin.test.Test

class FinderTest {
    @Test
    fun `finder returns empty list if content does not exist`() {
        // Given
        val item = Item("parent")
        val child = Item("child")
        val sibling = Item("sibling")
        val grandChild = Item("grandChild")
        child.add(grandChild)
        item.add(child)
        item.add(sibling)

        // When
        val result = item.findItemContaining("non-existing content")

        // Then
        result.shouldBeEmpty()
    }
}