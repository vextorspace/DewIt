package model.visitors

import model.Item

class PedigreeFinder(val rootItem: Item) {
    private val familyTree = mutableListOf<Item>()

    fun findPedigree(item: Item): List<Item> {
        return if (item == rootItem) {
            listOf(item)
        } else if(rootItem.subItems.isNotEmpty()) {
            familyTree.add(rootItem)
            rootItem.subItems.map {
                PedigreeFinder(it).findPedigree(item)
            }.filter {
                it.last() == item
            }.flatten()
                .also {
                    familyTree.addAll(it)
                }
            familyTree
        } else {
            emptyList()
        }
    }
}
