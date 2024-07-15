package model.visitors

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import model.Item
import kotlin.test.Test

class PedigreeFinderTest {

    @Test
    fun `looking for root finds list of just root`() {
        // Given
        val root = Item("Root")
        val finder = PedigreeFinder(root)

        // When
        val pedigree = finder.findPedigree(root)

        // Then
        pedigree.shouldContainExactly(root)
    }

    @Test
    fun `looking for pedigree backwards returns empty list`() {
        // Given
        val root = Item("Root")
        val child = Item("Child")
        root.add(child)

        val finder = PedigreeFinder(child)

        // When
        val pedigree = finder.findPedigree(root)

        // Then
        pedigree.shouldBeEmpty()
    }

    @Test
    fun `looking for pedigree forwards returns list of items`() {
        // Given
        val root = Item("Root")
        val child = Item("Child")
        root.add(child)

        val finder = PedigreeFinder(root)

        // When
        val pedigree = finder.findPedigree(child)

        // Then
        pedigree.shouldContainExactly(root, child)
    }

    @Test
    fun `looking for pedigree works with multiple children`() {
        // Given
        val root = Item("Root")
        val child1 = Item("Child1")
        val child2 = Item("Child2")
        root.add(child1)
        root.add(child2)

        val finder = PedigreeFinder(root)

        // When
        val pedigree = finder.findPedigree(child2)

        // Then
        pedigree.shouldContainExactly(root, child2)
    }

    @Test
    fun `looking for pedigree works with multiple generations`() {
        // Given
        val root = Item("Root")
        val child1 = Item("Child1")
        val child2 = Item("Child2")
        val grandchild = Item("Grandchild")
        root.add(child1)
        root.add(child2)
        child2.add(grandchild)

        val finder = PedigreeFinder(root)

        // When
        val pedigree = finder.findPedigree(grandchild)

        // Then
        pedigree.shouldContainExactly(root, child2, grandchild)
    }

    @Test
    fun `looking for pedigree works with multiple generations and multiple children`() {
        // Given
        val root = Item("Root")
        val child1 = Item("Child1")
        val child2 = Item("Child2")
        val grandchild1 = Item("Grandchild1")
        val grandchild2 = Item("Grandchild2")
        root.add(child1)
        root.add(child2)
        child2.add(grandchild1)
        child2.add(grandchild2)

        val finder = PedigreeFinder(root)

        // When
        val pedigree = finder.findPedigree(child1)

        // Then
        pedigree.shouldContainExactly(root, child1)
    }

    @Test
    fun `findChildToItem works one level down`() {
        // Given
        val root = Item("Root")
        val child = Item("Child")
        val grandChild = Item("Grandchild")
        root.add(child)
        child.add(grandChild)

        val finder = PedigreeFinder(root)

        // When
        val pedigree = finder.findChildToItem(grandChild)

        // Then
        pedigree.shouldContainExactly(child, grandChild)
    }

    @Test
    fun `findChildToItem returns empty list if no path`() {
        // Given
        val root = Item("Root")
        val child = Item("Child")
        val child2 = Item("Child2")
        val grandchild2 = Item("Grandchild2")
        val grandChild = Item("Grandchild")
        root.add(child)
        root.add(child2)
        child.add(grandChild)
        child2.add(grandchild2)

        val finder = PedigreeFinder(child)

        // When
        val pedigree = finder.findChildToItem(grandchild2)

        // Then
        pedigree.shouldBeEmpty()

    }
}