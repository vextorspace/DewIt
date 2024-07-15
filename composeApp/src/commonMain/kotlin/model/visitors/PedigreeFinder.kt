package model.visitors

import model.Item

class PedigreeFinder(val rootItem: Item) {
    private val familyTree = mutableListOf<Item>()

    fun findPedigree(item: Item): List<Item> {
        if(item != rootItem)
            return emptyList()
        return listOf(item)
    }
}
