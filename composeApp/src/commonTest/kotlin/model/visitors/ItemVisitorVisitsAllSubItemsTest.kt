package model.visitors

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import model.Item
import kotlin.test.Test

class ItemVisitorVisitsAllSubItemsTest {

    @Test
    fun `ItemVisitor visits all sub items`() {
        // Given
        val item = Item("parent")
        val child = Item("child")
        val sibling = Item("sibling")
        val grandChild = Item("grandChild")
        child.add(grandChild)
        item.add(child)
        item.add(sibling)

        val itemCollector = ItemVisitorCollector()
        val visitor = ItemVisitor(itemCollector)

        // When
        visitor.visit(item)

        // Then
        itemCollector.items shouldContainExactlyInAnyOrder  listOf(item, child, sibling, grandChild)
    }

    @Test
    fun `ItemVisitor starting at child does not get other branches`() {
        // Given
        val item = Item("parent")
        val child = Item("child")
        val sibling = Item("sibling")
        val grandChild = Item("grandChild")
        child.add(grandChild)
        item.add(child)
        item.add(sibling)

        val itemCollector = ItemVisitorCollector()
        val visitor = ItemVisitor(itemCollector)

        // When
        visitor.visit(child)

        // Then
        itemCollector.items shouldContainExactlyInAnyOrder  listOf(child, grandChild)
    }
}