package model.visitors

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

}