package model

import ids.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import model.visitors.FindItemByIdAction
import model.visitors.ItemVisitor
import model.visitors.PedigreeFinder

@Serializable
data class Item(
    var content: String = "New Item",
    val subItems: MutableList<Item> = mutableListOf(),
    val id: String = UUID.generateUUID(),
    val workflow: MutableList<ItemWorkflow> = mutableListOf()
) {
    constructor(content: String) : this(content, mutableListOf())
    constructor(content: String, subItems: Collection<Item>) : this(content, subItems.toMutableList())

    val workflows: MutableList<ItemWorkflow> = mutableListOf()

    fun add(subItem: Item) {
        if(subItems.contains(subItem))
            return
        subItems.add(subItem)
    }

    fun remove(subItem: Item) {
        subItems.remove(subItem)
    }

    fun findItemContaining(content: String): List<Item> {
        val finder = ItemVisitorActionFindItemContaining(content)
        ItemVisitor(finder).visit(this)
        return finder.items
    }

    fun toJson(): String {
        return encoder
            .encodeToString(serializer(), this)
    }

    fun findItemById(idToFind: String): Item? {
        val findItemByIdAction = FindItemByIdAction(idToFind)
        ItemVisitor(findItemByIdAction).visit(this)
        return findItemByIdAction.item
    }

    fun addWorkflow(itemWorkflow: ItemWorkflow) {
        workflows.add(itemWorkflow)
    }

    fun findFirstWorkflows(pedigreeFinder: PedigreeFinder): List<ItemWorkflow> {
        if(workflows.isNotEmpty())
            return workflows.toList()

        val pedigree = pedigreeFinder.findPedigree(this)
        if(pedigree.size < 2)
            return emptyList()
        return pedigree.reversed().drop(1).first().findFirstWorkflows(pedigreeFinder)
    }

    companion object {
        val encoder = Json { ignoreUnknownKeys = true }

        fun fromJson(itemJson: String): Item? {
            return encoder.decodeFromString<Item?>(itemJson)
        }
    }
}