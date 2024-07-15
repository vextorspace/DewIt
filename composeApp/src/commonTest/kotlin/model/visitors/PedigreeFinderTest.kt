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
}