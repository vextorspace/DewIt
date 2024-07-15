package model.visitors

import model.Item

class PedigreeFinder(val rootItem: Item) {

    fun findPedigree(item: Item): List<Item> {
        return if (item == rootItem) {
            listOf(item)
        } else if(rootItem.subItems.isNotEmpty()) {
            listOf(rootItem) + findChildToItem(item)
        } else {
            emptyList()
        }
    }

    fun findChildToItem(item: Item): List<Item> {
        return rootItem.subItems.map {
            PedigreeFinder(it).findPedigree(item)
        }.filter {
            lastItemIsItem(it, item)
        }.flatten()
    }

    private fun lastItemIsItem(it: List<Item>, item: Item) = it.isNotEmpty() && it.last() == item
}
