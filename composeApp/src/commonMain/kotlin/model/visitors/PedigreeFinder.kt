package model.visitors

import model.Item

class PedigreeFinder(rootItem: Item) {
    private val familyTree = mutableListOf<Item>()

    fun findPedigree(item: Item): List<Item> {
        return listOf(item)
    }
}
